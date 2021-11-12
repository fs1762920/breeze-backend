package cn.fan.breeze.service;

import cn.fan.breeze.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    boolean initWebmasterInfo(UserEntity userEntity, HttpServletRequest request);

    List<UserEntity> find(UserEntity queryParam);

    void update(UserEntity loginUpdate);

    UserEntity getById(int userId);
}
