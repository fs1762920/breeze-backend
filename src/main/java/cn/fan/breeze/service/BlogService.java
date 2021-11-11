package cn.fan.breeze.service;

import cn.fan.breeze.entity.BlogEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BlogService {
    void save(BlogEntity blogEntity);

    void deleteById(Integer blogId);

    void publish(BlogEntity param);

    void move(BlogEntity param);

    PageInfo<BlogEntity> findByPage(BlogEntity blogEntity, Integer pageNum, Integer pageSize);

    void update(BlogEntity blogEntity);

    BlogEntity findById(Integer blogId);

    List<BlogEntity> findLatest();
}
