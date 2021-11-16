package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.CustomMapper;
import cn.fan.breeze.dao.UserMapper;
import cn.fan.breeze.entity.CustomEntity;
import cn.fan.breeze.entity.UserEntity;
import cn.fan.breeze.service.UserService;
import cn.fan.breeze.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CustomMapper customMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initWebmasterInfo(UserEntity userEntity, HttpServletRequest request) {
        boolean result = false;
        List<UserEntity> userEntityList = userMapper.selectBySelective(new UserEntity());
        if (userEntityList.isEmpty()) {
            Date nowDate = new Date();
            userEntity.setCtime(nowDate);
            userEntity.setMtime(nowDate);
            userMapper.insert(userEntity);

//            String masterIpAddr = HttpUtils.getIpAddr(request);
            CustomEntity customEntity = new CustomEntity();
            customEntity.setMail(userEntity.getMail());
            customEntity.setNickname(userEntity.getNickname());
//            customEntity.setCustomIp(masterIpAddr);
            customEntity.setCustomType(1);
            customEntity.setCtime(nowDate);
            customEntity.setMtime(nowDate);
            customEntity.setLastVisitTime(nowDate);
            customMapper.insert(customEntity);
            result = true;
        }
        return result;
    }

    @Override
    public List<UserEntity> find(UserEntity queryParam) {
        return userMapper.selectBySelective(queryParam);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserEntity loginUpdate) {
        Date nowDate = new Date();
        loginUpdate.setMtime(nowDate);
        userMapper.updateByPrimaryKeySelective(loginUpdate);
        CustomEntity customEntity = new CustomEntity();
        customEntity.setAvatarPath(loginUpdate.getAvatar());
        customEntity.setNickname(loginUpdate.getNickname());
        customEntity.setMail(loginUpdate.getMail());
        customEntity.setHomePath(loginUpdate.getHomePath());
        customEntity.setCustomType(1);
        customEntity.setMtime(nowDate);
        customMapper.updateByCustomType(customEntity);
    }

    @Override
    public UserEntity getById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
