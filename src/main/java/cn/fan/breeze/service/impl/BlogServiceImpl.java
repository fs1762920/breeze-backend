package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.BlogLabelMapper;
import cn.fan.breeze.dao.BlogMapper;
import cn.fan.breeze.dao.OperationMapper;
import cn.fan.breeze.entity.BlogEntity;
import cn.fan.breeze.entity.BlogLabelEntity;
import cn.fan.breeze.entity.OperationEntity;
import cn.fan.breeze.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogLabelMapper blogLabelMapper;

    @Autowired
    private OperationMapper operationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BlogEntity blogEntity) {
        Date nowDate = new Date();
        blogEntity.setMtime(nowDate);
        OperationEntity operationEntity = new OperationEntity();
        operationEntity.setCtime(nowDate);
        operationEntity.setMtime(nowDate);
        operationEntity.setRelevance(blogEntity.getTitle());
        if (blogEntity.getBlogId() != null) { //更新
            operationEntity.setAction("文章更新");
            blogMapper.updateByPrimaryKeySelective(blogEntity);
            blogLabelMapper.deleteByBlogId(blogEntity.getBlogId());
        } else { //新增
            operationEntity.setAction("文章发布");
            blogEntity.setReadingCount(0);
            blogEntity.setCtime(nowDate);
            blogMapper.insert(blogEntity);
        }
        operationMapper.insert(operationEntity);
        if (blogEntity.getLabelIds() != null && blogEntity.getLabelIds().length > 0) {
            List<BlogLabelEntity> blogLabelEntityList = new ArrayList<>();
            for (Integer labelId : blogEntity.getLabelIds()) {
                BlogLabelEntity blogLabelEntity = new BlogLabelEntity();
                blogLabelEntity.setBlogId(blogEntity.getBlogId());
                blogLabelEntity.setLabelId(labelId);
                blogLabelEntityList.add(blogLabelEntity);
            }
            blogLabelMapper.insertBatch(blogLabelEntityList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer blogId) {
        BlogEntity deleteBlogEntity = blogMapper.selectByPrimaryKey(blogId);
        if (deleteBlogEntity != null) {
            Date nowDate = new Date();
            OperationEntity operationEntity = new OperationEntity();
            operationEntity.setCtime(nowDate);
            operationEntity.setMtime(nowDate);
            operationEntity.setAction("文章删除");
            operationEntity.setRelevance(deleteBlogEntity.getTitle());
            operationMapper.insert(operationEntity);
        }
        blogMapper.deleteByPrimaryKey(blogId);
        blogLabelMapper.deleteByBlogId(blogId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publish(BlogEntity param) {
        blogMapper.updateByPrimaryKeySelective(param);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void move(BlogEntity param) {
        blogMapper.updateByPrimaryKeySelective(param);
    }

    @Override
    public PageInfo<BlogEntity> findByPage(BlogEntity blogEntity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogEntity> blogEntityList = blogMapper.selectBySelective(blogEntity);
        return new PageInfo<>(blogEntityList);
    }

    @Override
    public void update(BlogEntity blogEntity) {
        blogMapper.updateByPrimaryKeySelective(blogEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogEntity findById(Integer blogId) {
        BlogEntity blogEntity = blogMapper.selectByPrimaryKey(blogId);
        if (blogEntity != null && !blogEntity.getLabelEntityList().isEmpty()) {
            // 更新阅读量
            blogMapper.increaseReadingCount(blogId);
            Integer[] labelIds = new Integer[blogEntity.getLabelEntityList().size()];
            for (int i = 0; i < blogEntity.getLabelEntityList().size(); i++) {
                labelIds[i] = blogEntity.getLabelEntityList().get(i).getLabelId();
            }
            blogEntity.setLabelIds(labelIds);
        }
        return blogEntity;
    }

    @Override
    public List<BlogEntity> findLatest() {
        return blogMapper.findLatest();
    }
}
