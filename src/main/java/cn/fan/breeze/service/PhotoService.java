package cn.fan.breeze.service;

import cn.fan.breeze.entity.PhotoEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {
    void upload(MultipartFile[] files) throws IOException;

    PageInfo<PhotoEntity> findByPage(Integer pageNum, Integer pageSize);

    PhotoEntity getById(Integer photoId);

    void deleteBatch(Integer[] photoIds);

    void deleteById(Integer photoId);
}
