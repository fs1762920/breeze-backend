package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.BlogMapper;
import cn.fan.breeze.dao.CommentMapper;
import cn.fan.breeze.entity.BlogEntity;
import cn.fan.breeze.entity.CommentEntity;
import cn.fan.breeze.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public boolean save(CommentEntity commentEntity) {
        boolean result = false;
        if (commentEntity.getBlogId() != null) {
            BlogEntity blogEntity = blogMapper.selectByPrimaryKey(commentEntity.getBlogId());
            if (blogEntity.getCommented() == 1) { // 如果博客开启评论
                commentMapper.insert(commentEntity);
                result= true;
            }
        } else {
            commentMapper.insert(commentEntity);  // 关于页面
        }
        return result;
    }

    @Override
    public PageInfo<CommentEntity> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentEntity> commentEntityList = commentMapper.selectBySelective(new CommentEntity());
        return new PageInfo<>(commentEntityList);
    }

    @Override
    public void delete(Integer commentId) {
        commentMapper.deleteByPrimaryKey(commentId);
    }
}
