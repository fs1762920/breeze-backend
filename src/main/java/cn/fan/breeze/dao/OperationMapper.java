package cn.fan.breeze.dao;

import cn.fan.breeze.entity.OperationEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationMapper {
    int deleteByPrimaryKey(Integer operationId);

    int insert(OperationEntity record);

    int insertSelective(OperationEntity record);

    OperationEntity selectByPrimaryKey(Integer operationId);

    int updateByPrimaryKeySelective(OperationEntity record);

    int updateByPrimaryKey(OperationEntity record);
}