package cn.fan.breeze.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.entity.BlogEntity;
import cn.fan.breeze.entity.OperationEntity;
import cn.fan.breeze.service.OperationService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operation")
@CrossOrigin
@Slf4j
public class OperationController {

    @Autowired
    private OperationService operationService;

    @SaCheckLogin
    @GetMapping("/findByPage")
    public BaseReturnDto findByPage(Integer pageNum, Integer pageSize) {
        PageInfo<OperationEntity> pageInfo = operationService.findByPage(pageNum, pageSize);
        return BaseReturnDto.success(pageInfo);
    }

    @SaCheckLogin
    @GetMapping("/findLatest")
    public BaseReturnDto findLatest() {
        List<OperationEntity> operationEntityList = operationService.findLatest();
        return BaseReturnDto.success(operationEntityList);
    }

}
