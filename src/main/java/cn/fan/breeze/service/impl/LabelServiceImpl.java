package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.LabelMapper;
import cn.fan.breeze.dao.OperationMapper;
import cn.fan.breeze.entity.LabelEntity;
import cn.fan.breeze.entity.OperationEntity;
import cn.fan.breeze.service.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private OperationMapper operationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(LabelEntity labelEntity) {
        Date nowDate = new Date();
        labelEntity.setCtime(nowDate);
        labelEntity.setMtime(nowDate);
        labelMapper.insert(labelEntity);

        OperationEntity operationEntity = new OperationEntity();
        operationEntity.setCtime(nowDate);
        operationEntity.setMtime(nowDate);
        operationEntity.setAction("标签新增");
        operationEntity.setRelevance(labelEntity.getLabelName());
        operationMapper.insert(operationEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(LabelEntity labelEntity) {
        Date nowDate = new Date();
        labelEntity.setMtime(nowDate);
        labelMapper.updateByPrimaryKeySelective(labelEntity);
    }

    @Override
    public List<LabelEntity> find(LabelEntity labelEntity) {
        return labelMapper.selectBySelective(labelEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer labelId) {
        LabelEntity deleteLabelEntity = labelMapper.selectByPrimaryKey(labelId);
        if (deleteLabelEntity != null) {
            Date nowDate = new Date();
            OperationEntity operationEntity = new OperationEntity();
            operationEntity.setCtime(nowDate);
            operationEntity.setMtime(nowDate);
            operationEntity.setAction("标签删除");
            operationEntity.setRelevance(deleteLabelEntity.getLabelName());
            operationMapper.insert(operationEntity);
        }
        labelMapper.deleteByPrimaryKey(labelId);
    }
}
