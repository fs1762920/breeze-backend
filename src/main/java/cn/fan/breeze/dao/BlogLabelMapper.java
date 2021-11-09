package cn.fan.breeze.dao;

import cn.fan.breeze.entity.BlogLabelEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogLabelMapper {
    int deleteByPrimaryKey(Integer blId);

    int insert(BlogLabelEntity record);

    int insertSelective(BlogLabelEntity record);

    BlogLabelEntity selectByPrimaryKey(Integer blId);

    int updateByPrimaryKeySelective(BlogLabelEntity record);

    int updateByPrimaryKey(BlogLabelEntity record);

    void insertBatch(@Param("blogLabelEntityList") List<BlogLabelEntity> blogLabelEntityList);
}