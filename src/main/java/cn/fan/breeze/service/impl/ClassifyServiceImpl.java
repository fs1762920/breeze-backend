package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.ClassifyMapper;
import cn.fan.breeze.dao.OperationMapper;
import cn.fan.breeze.entity.ClassifyEntity;
import cn.fan.breeze.entity.OperationEntity;
import cn.fan.breeze.service.ClassifyService;
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
public class ClassifyServiceImpl implements ClassifyService {

    @Autowired
    private ClassifyMapper classifyMapper;

    @Autowired
    private OperationMapper operationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ClassifyEntity classifyEntity) {
        Date nowDate = new Date();
        classifyEntity.setCtime(nowDate);
        classifyEntity.setMtime(nowDate);
        classifyMapper.insert(classifyEntity);

        OperationEntity operationEntity = new OperationEntity();
        operationEntity.setCtime(nowDate);
        operationEntity.setMtime(nowDate);
        operationEntity.setAction("分类新增");
        operationEntity.setRelevance(classifyEntity.getClassifyName());
        operationMapper.insert(operationEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ClassifyEntity classifyEntity) {
        Date nowDate = new Date();
        classifyEntity.setMtime(nowDate);
        classifyMapper.updateByPrimaryKeySelective(classifyEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer classifyId) {
        ClassifyEntity deleteClassifyEntity = classifyMapper.selectByPrimaryKey(classifyId);
        if (deleteClassifyEntity != null) {
            Date nowDate = new Date();
            OperationEntity operationEntity = new OperationEntity();
            operationEntity.setCtime(nowDate);
            operationEntity.setMtime(nowDate);
            operationEntity.setAction("分类删除");
            operationEntity.setRelevance(deleteClassifyEntity.getClassifyName());
            operationMapper.insert(operationEntity);
        }
        classifyMapper.deleteByPrimaryKey(classifyId);
    }

    @Override
    public PageInfo<ClassifyEntity> findByPage(ClassifyEntity classifyEntity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ClassifyEntity> classifyEntityList = classifyMapper.selectBySelective(classifyEntity);
        return new PageInfo<>(classifyEntityList);
    }

    @Override
    public List<ClassifyEntity> find(ClassifyEntity classifyEntity) {
        return classifyMapper.selectBySelective(classifyEntity);
    }
}
