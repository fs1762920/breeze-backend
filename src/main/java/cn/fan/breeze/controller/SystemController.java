package cn.fan.breeze.controller;

import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.constant.ExceptionEnum;
import cn.fan.breeze.entity.UserEntity;
import cn.fan.breeze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private UserService userService;

    @PostMapping("/initWebmasterInfo")
    public BaseReturnDto initWebmasterInfo(@RequestBody UserEntity userEntity) {
        boolean initResult = userService.initWebmasterInfo(userEntity);
        BaseReturnDto result;
        if (initResult) {
            result = BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "站长信息初始化完成...");
        } else {
            result = BaseReturnDto.error(ExceptionEnum.INIT_WEBMASTER_REPEAT);
        }
        return result;
    }
}
