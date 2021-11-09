package cn.fan.breeze.controller;

import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.entity.BlogEntity;
import cn.fan.breeze.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/save")
    public BaseReturnDto save(@RequestBody BlogEntity blogEntity) {
        blogService.save(blogEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "发布成功");
    }

}
