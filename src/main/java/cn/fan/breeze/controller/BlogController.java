package cn.fan.breeze.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.entity.BlogEntity;
import cn.fan.breeze.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
@Slf4j
@CrossOrigin
public class BlogController {

    @Autowired
    private BlogService blogService;

    @SaCheckLogin
    @PostMapping("/save")
    public BaseReturnDto save(@RequestBody BlogEntity blogEntity) {
        blogService.save(blogEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "发布成功");
    }

}
