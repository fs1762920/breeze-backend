package cn.fan.breeze.dao;

import cn.fan.breeze.entity.ShorthandEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShorthandMapper {
    int deleteByPrimaryKey(Integer shorthandId);

    int insert(ShorthandEntity record);

    ShorthandEntity selectByPrimaryKey(Integer shorthandId);

    int updateByPrimaryKeySelective(ShorthandEntity record);

    int updateByPrimaryKey(ShorthandEntity record);

    List<ShorthandEntity> selectBySelective(ShorthandEntity shorthandEntity);
}