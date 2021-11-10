package cn.fan.breeze.service;

import cn.fan.breeze.entity.FriendEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FriendService {

    void apply(FriendEntity friendEntity);

    PageInfo<FriendEntity> findByPage(Integer pageNum, Integer pageSize);

    void audit(FriendEntity friendEntity);

    List<FriendEntity> find();

    void deleteById(Integer friendId);
}
