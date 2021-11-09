package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.UserMapper;
import cn.fan.breeze.entity.UserEntity;
import cn.fan.breeze.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean initWebmasterInfo(UserEntity userEntity) {
        boolean result = false;
        List<UserEntity> userEntityList = userMapper.selectBySelective(new UserEntity());
        if (userEntityList.isEmpty()) {

            userMapper.insert(userEntity);
            result = true;
        }
        return result;
    }

    @Override
    public List<UserEntity> find(UserEntity queryParam) {
        return userMapper.selectBySelective(queryParam);
    }

    @Override
    public void update(UserEntity loginUpdate) {
        userMapper.updateByPrimaryKeySelective(loginUpdate);
    }

    @Override
    public UserEntity getById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
