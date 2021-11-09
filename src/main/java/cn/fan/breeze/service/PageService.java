package cn.fan.breeze.service;

import cn.fan.breeze.entity.PageEntity;

public interface PageService {
    PageEntity find(PageEntity pageEntity);

    void save(PageEntity pageEntity);
}
