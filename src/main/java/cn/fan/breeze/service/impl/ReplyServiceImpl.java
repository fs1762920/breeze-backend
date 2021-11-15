package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.BlogMapper;
import cn.fan.breeze.dao.ReplyMapper;
import cn.fan.breeze.entity.BlogEntity;
import cn.fan.breeze.entity.ReplyEntity;
import cn.fan.breeze.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public void delete(Integer replyId) {
        replyMapper.deleteByPrimaryKey(replyId);
    }

    @Override
    public boolean save(ReplyEntity replyEntity) {
        boolean result = false;
        if (replyEntity.getBlogId() != null) {
            BlogEntity blogEntity = blogMapper.selectByPrimaryKey(replyEntity.getBlogId());
            if (blogEntity.getCommented() == 1) { // 如果博客开启评论
                replyMapper.insert(replyEntity);
                result= true;
            }
        } else {
            replyMapper.insert(replyEntity);  // 关于页面
        }
        return result;
    }
}
