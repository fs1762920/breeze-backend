package cn.fan.breeze.service;

import cn.fan.breeze.entity.LabelEntity;

import java.util.List;

public interface LabelService {
    void save(LabelEntity labelEntity);

    void update(LabelEntity labelEntity);

    List<LabelEntity> find(LabelEntity labelEntity);

    void deleteById(Integer labelId);
}
