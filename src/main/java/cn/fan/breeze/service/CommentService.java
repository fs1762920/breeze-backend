package cn.fan.breeze.service;

import cn.fan.breeze.entity.CommentEntity;
import com.github.pagehelper.PageInfo;

public interface CommentService {
    boolean save(CommentEntity commentEntity);

    PageInfo<CommentEntity> findByPage(Integer pageNum, Integer pageSize);

    void delete(Integer commentId);
}
