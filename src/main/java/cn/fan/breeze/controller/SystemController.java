package cn.fan.breeze.controller;

import cn.fan.breeze.common.BaseReturnDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class SystemController {

    @PostMapping("/initWebmasterInfo")
    public BaseReturnDto initWebmasterInfo() {
        return BaseReturnDto.success("站长信息初始化成功...");
    }

    @PostMapping("/initWebsiteInfo")
    public BaseReturnDto initWebsiteInfo() {
        return BaseReturnDto.success("网站信息初始化成功...");
    }
}
