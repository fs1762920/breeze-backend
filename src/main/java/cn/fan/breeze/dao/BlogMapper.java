package cn.fan.breeze.dao;

import cn.fan.breeze.entity.BlogEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper {
    int deleteByPrimaryKey(Integer blogId);

    int insert(BlogEntity record);

    int insertSelective(BlogEntity record);

    BlogEntity selectByPrimaryKey(Integer blogId);

    int updateByPrimaryKeySelective(BlogEntity record);

    int updateByPrimaryKey(BlogEntity record);
}