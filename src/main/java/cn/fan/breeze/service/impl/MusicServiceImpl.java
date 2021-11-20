package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.MusicMapper;
import cn.fan.breeze.entity.MusicEntity;
import cn.fan.breeze.service.FileService;
import cn.fan.breeze.service.MusicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import eu.medsea.mimeutil.MimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class MusicServiceImpl implements MusicService {

    @Value("${upload.target-path}")
    private String targetPath;

    @Value("${upload.path-prefix}")
    private String pathPrefix;

    @Autowired
    private MusicMapper musicMapper;

    @Autowired
    private FileService fileService;

    @Override
    public List<MusicEntity> findAll() {
        return musicMapper.selectBySelective();
    }

    @Override
    public PageInfo<MusicEntity> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MusicEntity> musicEntityList = musicMapper.selectBySelective();
        return new PageInfo<>(musicEntityList);
    }

    @Override
    public void save(MultipartFile file, String lyric) throws IOException, InvalidDataException, UnsupportedTagException {
        Date nowDate = new Date();
        String fileName = fileService.upload(file);
        MusicEntity musicEntity = analyze(fileName);
        musicEntity.setSrc(pathPrefix + "/" + fileName);
        musicEntity.setSize(file.getSize());
        musicEntity.setLyric(lyric);
        musicEntity.setCtime(nowDate);
        musicEntity.setMtime(nowDate);
        musicMapper.insert(musicEntity);
    }

    @Override
    public void delete(Integer musicId) {
        musicMapper.deleteByPrimaryKey(musicId);
    }

    private MusicEntity analyze(String fileName) throws InvalidDataException, UnsupportedTagException, IOException {
        MusicEntity result = null;
        String realPath = targetPath + "/" + fileName;
        Mp3File mp3file = new Mp3File(realPath);
        if (mp3file.hasId3v2Tag()) {
            result = new MusicEntity();
            Long duration = mp3file.getLengthInSeconds();
            result.setDuration(duration);
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            String artist = id3v2Tag.getArtist();
            result.setArtist(artist);
            String title = id3v2Tag.getTitle();
            result.setTitle(title);
            String album = id3v2Tag.getAlbum();
            result.setAlbum(album);
            byte[] albumImageData = id3v2Tag.getAlbumImage();
            if (albumImageData != null) {

                String suffix = "." + MimeUtil.getSubType(id3v2Tag.getAlbumImageMimeType());
                String finalName = UUID.randomUUID().toString().replaceAll("-", "");
                String filePath = targetPath + '/' + finalName+suffix;
                String fileRefer = pathPrefix + "/"+finalName+suffix;

                File file  = new File(filePath);
                try (FileOutputStream fos = new FileOutputStream(file); BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                    bos.write(albumImageData);
                    result.setAlbumPicture(fileRefer);
                } catch (Exception e) {
                    log.info("专辑图片上传失败：", e);
                }
            }
        }
        return result;
    }
}
