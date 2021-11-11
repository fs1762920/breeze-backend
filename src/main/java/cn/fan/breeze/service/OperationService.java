package cn.fan.breeze.service;

import cn.fan.breeze.entity.OperationEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OperationService {
    PageInfo<OperationEntity> findByPage(Integer pageNum, Integer pageSize);

    List<OperationEntity> findLatest();
}
