package cn.fan.breeze.service;

import cn.fan.breeze.entity.ClassifyEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ClassifyService {
    void save(ClassifyEntity classifyEntity);

    void update(ClassifyEntity classifyEntity);

    void deleteById(Integer classifyId);

    PageInfo<ClassifyEntity> findByPage(ClassifyEntity classifyEntity, Integer pageNum, Integer pageSize);

    List<ClassifyEntity> find(ClassifyEntity classifyEntity);
}
