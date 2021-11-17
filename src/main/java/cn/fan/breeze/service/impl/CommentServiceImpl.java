package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.BlogMapper;
import cn.fan.breeze.dao.CommentMapper;
import cn.fan.breeze.dao.CustomMapper;
import cn.fan.breeze.dao.ReplyMapper;
import cn.fan.breeze.entity.BlogEntity;
import cn.fan.breeze.entity.CommentEntity;
import cn.fan.breeze.entity.CustomEntity;
import cn.fan.breeze.entity.ReplyEntity;
import cn.fan.breeze.entity.vo.CommentInfo;
import cn.fan.breeze.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private CustomMapper customMapper;

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(CommentInfo commentInfo) {
        boolean result = false;
        Date nowDate = new Date();
//        if (commentInfo.getBlogId() != null) {
//            BlogEntity blogEntity = blogMapper.selectByPrimaryKey(commentInfo.getBlogId());
//            if (blogEntity.getCommented() == 1) { // 如果博客开启评论
//                commentMapper.insert(commentInfo);
                CustomEntity customEntity = new CustomEntity();
                customEntity.setLastVisitTime(nowDate);
                customEntity.setMtime(nowDate);
                customEntity.setCustomIp(commentInfo.getCustomIp());
                customEntity.setNickname(commentInfo.getNickname());
                customEntity.setMail(commentInfo.getMail());
                customEntity.setHomePath(commentInfo.getHomePath());
                customMapper.updateByIpAddr(customEntity);
                if(commentInfo.getTargetId() == null) { //评论
                    CommentEntity commentEntity = new CommentEntity();
                    commentEntity.setBlogId(commentInfo.getBlogId());
                    commentEntity.setContent(commentInfo.getContent());
                    commentEntity.setIsValid(1);
                    commentEntity.setSrcId(commentInfo.getSrcId());
                    commentEntity.setCtime(nowDate);
                    commentEntity.setMtime(nowDate);
                    commentMapper.insert(commentEntity);
                } else { //回复
                    ReplyEntity replyEntity = new ReplyEntity();
                    replyEntity.setBlogId(commentInfo.getBlogId());
                    replyEntity.setCommentId(commentInfo.getCommentId());
                    replyEntity.setContent(commentInfo.getContent());
                    replyEntity.setIsValid(1);
                    replyEntity.setSrcId(commentInfo.getSrcId());
                    replyEntity.setTargetId(commentInfo.getTargetId());
                    replyEntity.setCtime(nowDate);
                    replyEntity.setMtime(nowDate);
                    replyMapper.insert(replyEntity);
                }
                result= true;
//            }
//        } else {
////            commentMapper.insert(commentInfo);  // 关于页面
//        }
        return result;
    }

    @Override
    public PageInfo<CommentEntity> findByPage(CommentEntity commentEntity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentEntity> commentEntityList = commentMapper.selectBySelective(commentEntity);
        return new PageInfo<>(commentEntityList);
    }

    @Override
    public PageInfo<CommentEntity> findByPageBackend(Integer type, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentEntity> commentEntityList;
        if (type == 0) {
            commentEntityList = commentMapper.selectByBlog();
        } else {
            commentEntityList = commentMapper.selectByPage();
        }
        return new PageInfo<>(commentEntityList);
    }

    @Override
    public void delete(Integer commentId) {
        commentMapper.deleteByPrimaryKey(commentId);
    }
}
