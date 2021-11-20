package cn.fan.breeze.dao;

import cn.fan.breeze.entity.MusicEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MusicMapper {
    int deleteByPrimaryKey(Integer musicId);

    int insert(MusicEntity record);

    int insertSelective(MusicEntity record);

    MusicEntity selectByPrimaryKey(Integer musicId);

    int updateByPrimaryKeySelective(MusicEntity record);

    int updateByPrimaryKey(MusicEntity record);

    List<MusicEntity> selectBySelective();
}