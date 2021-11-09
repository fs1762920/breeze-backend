package cn.fan.breeze.dao;

import cn.fan.breeze.entity.CustomEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomMapper {
    int deleteByPrimaryKey(Integer customId);

    int insert(CustomEntity record);

    int insertSelective(CustomEntity record);

    CustomEntity selectByPrimaryKey(Integer customId);

    int updateByPrimaryKeySelective(CustomEntity record);

    int updateByPrimaryKey(CustomEntity record);
}