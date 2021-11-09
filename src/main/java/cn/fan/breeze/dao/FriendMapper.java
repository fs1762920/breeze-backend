package cn.fan.breeze.dao;

import cn.fan.breeze.entity.FriendEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FriendMapper {
    int deleteByPrimaryKey(Integer friendId);

    int insert(FriendEntity record);

    int insertSelective(FriendEntity record);

    FriendEntity selectByPrimaryKey(Integer friendId);

    int updateByPrimaryKeySelective(FriendEntity record);

    int updateByPrimaryKey(FriendEntity record);
}