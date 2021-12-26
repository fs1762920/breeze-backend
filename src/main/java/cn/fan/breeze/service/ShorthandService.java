package cn.fan.breeze.service;

import cn.fan.breeze.entity.ShorthandEntity;
import com.github.pagehelper.PageInfo;

public interface ShorthandService {
    PageInfo<ShorthandEntity> findByPage(Integer pageNum, Integer pageSize, int type);

    void save(ShorthandEntity shorthandEntity);

    void update(ShorthandEntity shorthandEntity);

    void delete(Integer shorthandId);

    ShorthandEntity findById(Integer shorthandId);
}
