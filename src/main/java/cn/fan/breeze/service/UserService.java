package cn.fan.breeze.service;

import cn.fan.breeze.entity.UserEntity;

import java.util.List;

public interface UserService {
    boolean initWebmasterInfo(UserEntity userEntity);

    List<UserEntity> find(UserEntity queryParam);

    void update(UserEntity loginUpdate);

    UserEntity getById(int userId);
}
