package cn.fan.breeze.dao;

import cn.fan.breeze.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(CommentEntity record);

    int insertSelective(CommentEntity record);

    CommentEntity selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(CommentEntity record);

    int updateByPrimaryKey(CommentEntity record);

    Integer getCount();

    List<CommentEntity> selectBySelective(CommentEntity commentEntity);
}