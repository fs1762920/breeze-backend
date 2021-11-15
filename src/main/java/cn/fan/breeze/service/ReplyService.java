package cn.fan.breeze.service;

import cn.fan.breeze.entity.ReplyEntity;

public interface ReplyService {
    void delete(Integer replyId);

    boolean save(ReplyEntity replyEntity);
}
