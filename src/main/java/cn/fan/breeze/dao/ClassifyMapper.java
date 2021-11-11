package cn.fan.breeze.dao;

import cn.fan.breeze.entity.ClassifyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassifyMapper {
    int deleteByPrimaryKey(Integer classifyId);

    int insert(ClassifyEntity record);

    int insertSelective(ClassifyEntity record);

    ClassifyEntity selectByPrimaryKey(Integer classifyId);

    List<ClassifyEntity> selectBySelective(ClassifyEntity classifyEntity);

    int updateByPrimaryKeySelective(ClassifyEntity record);

    int updateByPrimaryKey(ClassifyEntity record);

    Integer getCount();
}