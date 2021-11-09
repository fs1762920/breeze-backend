package cn.fan.breeze.dao;

import cn.fan.breeze.entity.LabelEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LabelMapper {
    int deleteByPrimaryKey(Integer labelId);

    int insert(LabelEntity record);

    int insertSelective(LabelEntity record);

    LabelEntity selectByPrimaryKey(Integer labelId);

    int updateByPrimaryKeySelective(LabelEntity record);

    int updateByPrimaryKey(LabelEntity record);

    List<LabelEntity> selectBySelective(LabelEntity labelEntity);
}