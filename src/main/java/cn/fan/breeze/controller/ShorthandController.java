package cn.fan.breeze.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.entity.ShorthandEntity;
import cn.fan.breeze.service.ShorthandService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shorthand")
@Slf4j
@CrossOrigin
public class ShorthandController {

    @Autowired
    private ShorthandService shorthandService;

    @GetMapping("/findByPage")
    public BaseReturnDto findByPage(Integer pageNum, Integer pageSize) {
        PageInfo<ShorthandEntity> pageInfo = shorthandService.findByPage(pageNum, pageSize, 1);
        return BaseReturnDto.success(pageInfo);
    }

    @SaCheckLogin
    @GetMapping("/findByPageBackend")
    public BaseReturnDto findByPageBackend(Integer pageNum, Integer pageSize) {
        PageInfo<ShorthandEntity> pageInfo = shorthandService.findByPage(pageNum, pageSize, 2);
        return BaseReturnDto.success(pageInfo);
    }

    @SaCheckLogin
    @PostMapping("/save")
    public BaseReturnDto save(@RequestBody ShorthandEntity shorthandEntity) {
        Integer userId = StpUtil.getLoginIdAsInt();
        shorthandEntity.setUserId(userId);
        shorthandService.save(shorthandEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "发表成功");
    }

    @SaCheckLogin
    @PostMapping("/update")
    public BaseReturnDto update(@RequestBody ShorthandEntity shorthandEntity) {
        shorthandService.update(shorthandEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "修改成功");
    }

    @SaCheckLogin
    @GetMapping("/delete")
    public BaseReturnDto delete(Integer shorthandId) {
        shorthandService.delete(shorthandId);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "删除成功");
    }

    @SaCheckLogin
    @GetMapping("/findById")
    public BaseReturnDto findById(Integer shorthandId) {
        ShorthandEntity shorthandEntity = shorthandService.findById(shorthandId);
        return BaseReturnDto.success(shorthandEntity);
    }
}
