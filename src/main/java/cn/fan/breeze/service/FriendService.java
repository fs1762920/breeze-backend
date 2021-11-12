package cn.fan.breeze.service;

import cn.fan.breeze.entity.FriendEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FriendService {

    void save(FriendEntity friendEntity);

    PageInfo<FriendEntity> findByPage(Integer pageNum, Integer pageSize);

    List<FriendEntity> find();

    void deleteById(Integer friendId);

    void update(FriendEntity friendEntity);
}
