package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.BlogLabelMapper;
import cn.fan.breeze.dao.BlogMapper;
import cn.fan.breeze.entity.BlogEntity;
import cn.fan.breeze.entity.BlogLabelEntity;
import cn.fan.breeze.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void save(BlogEntity blogEntity) {
        Date nowDate = new Date();
        blogEntity.setReadingCount(0);
        blogEntity.setCtime(nowDate);
        blogEntity.setMtime(nowDate);
        blogMapper.insert(blogEntity);
        if (blogEntity.getLabelIds() != null && blogEntity.getLabelIds().length>0) {
            List<BlogLabelEntity> blogLabelEntityList = new ArrayList<>();
            for (Integer labelId:blogEntity.getLabelIds()) {
                BlogLabelEntity blogLabelEntity = new BlogLabelEntity();
                blogLabelEntity.setBlogId(blogEntity.getBlogId());
                blogLabelEntity.setLabelId(labelId);
                blogLabelEntityList.add(blogLabelEntity);
            }
            log.info("blogLabelEntityList: {}", blogLabelEntityList);
            blogLabelMapper.insertBatch(blogLabelEntityList);
        }
    }
}
