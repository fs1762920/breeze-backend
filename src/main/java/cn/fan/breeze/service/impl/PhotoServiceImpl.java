package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.PhotoMapper;
import cn.fan.breeze.entity.PhotoEntity;
import cn.fan.breeze.service.PhotoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    @Value("${upload.target-path}")
    private String targetPath;

    @Value("${upload.path-prefix}")
    private String pathPrefix;

    @Override
    public void upload(MultipartFile[] files) throws IOException {
        List<PhotoEntity> photoEntityList = new ArrayList<>();
        Date nowDate = new Date();
        for (MultipartFile file : files) {
            String originalFileName = file.getOriginalFilename();
            String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            String dealedFileName = UUID.randomUUID().toString().replace("-", "") + fileSuffix;
            File dest = new File(targetPath + '/' + dealedFileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            BufferedImage image = ImageIO.read(file.getInputStream());
            int width = image.getWidth();
            int height = image.getHeight();
            long size = file.getSize();
            String mimeType = file.getContentType();
            file.transferTo(dest);
            PhotoEntity photoEntity = new PhotoEntity();
            photoEntity.setDisplayName(originalFileName);
            photoEntity.setOriginalName(dealedFileName);
            photoEntity.setPhotoPath(pathPrefix + '/' + dealedFileName);
            photoEntity.setCtime(nowDate);
            photoEntity.setMtime(nowDate);
            photoEntityList.add(photoEntity);
        }
        photoMapper.insertBatch(photoEntityList);
    }

    @Override
    public PageInfo<PhotoEntity> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PhotoEntity> photoEntityList = photoMapper.selectBySelective(new PhotoEntity());
        return new PageInfo<>(photoEntityList);
    }

    @Override
    public PhotoEntity getById(Integer photoId) {
        return photoMapper.selectByPrimaryKey(photoId);
    }

    @Override
    public void deleteBatch(Integer[] photoIds) {
        photoMapper.deleteBatch(photoIds);
    }

    @Override
    public void deleteById(Integer photoId) {
        photoMapper.deleteByPrimaryKey(photoId);
    }
}
