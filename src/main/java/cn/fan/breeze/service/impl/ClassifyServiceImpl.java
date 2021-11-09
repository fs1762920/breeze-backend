package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.ClassifyMapper;
import cn.fan.breeze.entity.ClassifyEntity;
import cn.fan.breeze.service.ClassifyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ClassifyServiceImpl implements ClassifyService {

    @Autowired
    private ClassifyMapper classifyMapper;

    @Override
    public void save(ClassifyEntity classifyEntity) {
        Date nowDate = new Date();
        classifyEntity.setCtime(nowDate);
        classifyEntity.setMtime(nowDate);
        classifyMapper.insert(classifyEntity);
    }

    @Override
    public void update(ClassifyEntity classifyEntity) {
        Date nowDate = new Date();
        classifyEntity.setMtime(nowDate);
        classifyMapper.updateByPrimaryKeySelective(classifyEntity);
    }

    @Override
    public void deleteById(Integer classifyId) {
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
