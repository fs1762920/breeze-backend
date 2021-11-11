package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.OperationMapper;
import cn.fan.breeze.dao.PageMapper;
import cn.fan.breeze.entity.OperationEntity;
import cn.fan.breeze.entity.PageEntity;
import cn.fan.breeze.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
public class PageServiceImpl implements PageService {

    @Autowired
    private PageMapper pageMapper;

    @Autowired
    private OperationMapper operationMapper;

    @Override
    public PageEntity find(PageEntity pageEntity) {
        return pageMapper.findOne(pageEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(PageEntity pageEntity) {
        Date nowDate = new Date();
        pageEntity.setMtime(nowDate);
        if (pageMapper.findOne(pageEntity) == null) {
            pageEntity.setCtime(nowDate);
            pageMapper.insert(pageEntity);
        } else {
            pageMapper.updateByPrimaryKey(pageEntity);
        }
        OperationEntity operationEntity = new OperationEntity();
        operationEntity.setCtime(nowDate);
        operationEntity.setMtime(nowDate);
        operationEntity.setAction("页面发布");
        operationEntity.setRelevance("《关于我》");
        operationMapper.insert(operationEntity);
    }
}
