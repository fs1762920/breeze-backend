package cn.fan.breeze.dao;

import cn.fan.breeze.entity.PageEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PageMapper {
    int deleteByPrimaryKey(Integer pageId);

    int insert(PageEntity record);

    int insertSelective(PageEntity record);

    PageEntity selectByPrimaryKey(Integer pageId);

    int updateByPrimaryKeySelective(PageEntity record);

    int updateByPrimaryKey(PageEntity record);

    PageEntity findOne(PageEntity record);
}