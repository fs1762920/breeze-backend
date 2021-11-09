package cn.fan.breeze.dao;

import cn.fan.breeze.entity.PhotoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PhotoMapper {
    int deleteByPrimaryKey(Integer photoId);

    int insert(PhotoEntity record);

    int insertSelective(PhotoEntity record);

    PhotoEntity selectByPrimaryKey(Integer photoId);

    int updateByPrimaryKeySelective(PhotoEntity record);

    int updateByPrimaryKey(PhotoEntity record);

    void deleteBatch(Integer[] photoIds);

    List<PhotoEntity> selectBySelective(PhotoEntity photoEntity);

    void insertBatch(@Param("photoEntityList") List<PhotoEntity> photoEntityList);
}