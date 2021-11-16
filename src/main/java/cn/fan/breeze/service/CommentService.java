package cn.fan.breeze.service;

import cn.fan.breeze.entity.CommentEntity;
import cn.fan.breeze.entity.vo.CommentInfo;
import com.github.pagehelper.PageInfo;

public interface CommentService {
    boolean save(CommentInfo commentInfo);

    PageInfo<CommentEntity> findByPage(CommentEntity commentEntity, Integer pageNum, Integer pageSize);

    void delete(Integer commentId);
}
