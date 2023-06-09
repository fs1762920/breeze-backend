package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.FriendMapper;
import cn.fan.breeze.entity.FriendEntity;
import cn.fan.breeze.service.FriendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public void save(FriendEntity friendEntity) {
        Date nowDate = new Date();
        friendEntity.setStatus(1);
        friendEntity.setCtime(nowDate);
        friendEntity.setMtime(nowDate);
        friendMapper.insert(friendEntity);
    }

    @Override
    public PageInfo<FriendEntity> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<FriendEntity> friendEntityList = friendMapper.selectBySelective(new FriendEntity());
        return new PageInfo<>(friendEntityList);
    }

    @Override
    public List<FriendEntity> find() {
        FriendEntity param = new FriendEntity();
        param.setStatus(1);
        return friendMapper.selectBySelective(param);
    }

    @Override
    public void deleteById(Integer friendId) {
        friendMapper.deleteByPrimaryKey(friendId);
    }

    @Override
    public void update(FriendEntity friendEntity) {
        friendEntity.setStatus(1);
        friendEntity.setMtime(new Date());
        friendMapper.updateByPrimaryKeySelective(friendEntity);
    }
}
