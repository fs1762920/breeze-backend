package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.OperationMapper;
import cn.fan.breeze.entity.OperationEntity;
import cn.fan.breeze.service.OperationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationMapper operationMapper;

    @Override
    public PageInfo<OperationEntity> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OperationEntity> operationEntityList = operationMapper.selectBySelective(new OperationEntity());
        return new PageInfo<>(operationEntityList);
    }

    @Override
    public List<OperationEntity> findLatest() {
        return operationMapper.findLatest();
    }
}
