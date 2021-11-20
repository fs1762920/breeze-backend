package cn.fan.breeze.service;

import cn.fan.breeze.entity.MusicEntity;
import com.github.pagehelper.PageInfo;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MusicService {
    List<MusicEntity> findAll();

    PageInfo<MusicEntity> findByPage(Integer pageNum, Integer pageSize);

    void save(MultipartFile file, String lyric) throws IOException, InvalidDataException, UnsupportedTagException;

    void delete(Integer musicId);
}
