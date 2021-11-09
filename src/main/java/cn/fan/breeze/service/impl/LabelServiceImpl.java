package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.LabelMapper;
import cn.fan.breeze.entity.LabelEntity;
import cn.fan.breeze.service.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelMapper labelMapper;

    @Override
    public void save(LabelEntity labelEntity) {
        Date nowDate = new Date();
        labelEntity.setCtime(nowDate);
        labelEntity.setMtime(nowDate);
        labelMapper.insert(labelEntity);
    }

    @Override
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
    public void deleteById(Integer labelId) {
        labelMapper.deleteByPrimaryKey(labelId);
    }
}
