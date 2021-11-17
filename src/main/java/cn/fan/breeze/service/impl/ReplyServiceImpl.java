package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.BlogMapper;
import cn.fan.breeze.dao.CustomMapper;
import cn.fan.breeze.dao.ReplyMapper;
import cn.fan.breeze.entity.BlogEntity;
import cn.fan.breeze.entity.CustomEntity;
import cn.fan.breeze.entity.ReplyEntity;
import cn.fan.breeze.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private CustomMapper customMapper;

    @Override
    public void delete(Integer replyId) {
        replyMapper.deleteByPrimaryKey(replyId);
    }

    @Override
    public boolean save(ReplyEntity replyEntity) {
        boolean result = false;
        CustomEntity param = new CustomEntity();
        param.setCustomType(1);
        List<CustomEntity> customEntityList =  customMapper.selectBySelective(param);
        replyEntity.setSrcId(customEntityList.get(0).getCustomId());
        Date nowDate = new Date();
        replyEntity.setCtime(nowDate);
        replyEntity.setMtime(nowDate);
        replyEntity.setIsValid(1);
        if (replyEntity.getBlogId() != null && replyEntity.getBlogId() != -1) {
            BlogEntity blogEntity = blogMapper.selectByPrimaryKey(replyEntity.getBlogId());
            if (blogEntity.getCommented() == 1) { // 如果博客开启评论
                replyMapper.insert(replyEntity);
                result= true;
            }
        } else {
            replyMapper.insert(replyEntity);  // 关于页面
            result= true;
        }
        return result;
    }
}
