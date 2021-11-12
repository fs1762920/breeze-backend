package cn.fan.breeze.service;

import cn.fan.breeze.entity.CustomEntity;

public interface CustomService {

    CustomEntity findMaster();

    CustomEntity findByIp(String customIpAddr);

    void save(CustomEntity customEntity);
}
