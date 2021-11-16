package cn.fan.breeze.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.entity.CustomEntity;
import cn.fan.breeze.service.CustomService;
import cn.fan.breeze.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/custom")
@CrossOrigin
@Slf4j
public class CustomController {

    @Autowired
    private CustomService customService;

    @GetMapping("/info")
    public BaseReturnDto info(HttpServletRequest request) {
        CustomEntity master;
        if (!StpUtil.isLogin()) {
            String customIpAddr = HttpUtils.getIpAddr(request);
            master = customService.findByIp(customIpAddr);
        } else {
            master = customService.findMaster();
        }
        return BaseReturnDto.success(master);
    }

    @PostMapping("/save")
    public BaseReturnDto save(HttpServletRequest request) {
        if (!StpUtil.isLogin()) {
            String customIpAddr = HttpUtils.getIpAddr(request);
            CustomEntity customEntity = new CustomEntity();
            customEntity.setCustomIp(customIpAddr);
            customService.save(customEntity);
        }
        return BaseReturnDto.success(null);
    }

    @PostMapping("/update")
    public BaseReturnDto update(@RequestBody CustomEntity customEntity, HttpServletRequest request) {
        String ipAddr = HttpUtils.getIpAddr(request);
        customEntity.setCustomIp(ipAddr);
        customService.updateByIpAddr(customEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "游客信息更新成功!");
    }
}
