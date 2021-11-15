package cn.fan.breeze.dao;

import cn.fan.breeze.entity.ReplyEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {
    int deleteByPrimaryKey(Integer replyId);

    int insert(ReplyEntity record);

    int insertSelective(ReplyEntity record);

    ReplyEntity selectByPrimaryKey(Integer replyId);

    int updateByPrimaryKeySelective(ReplyEntity record);

    int updateByPrimaryKey(ReplyEntity record);

    Integer getCount();
}