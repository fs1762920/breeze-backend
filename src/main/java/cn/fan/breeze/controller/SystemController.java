package cn.fan.breeze.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.constant.ExceptionEnum;
import cn.fan.breeze.entity.UserEntity;
import cn.fan.breeze.service.SystemService;
import cn.fan.breeze.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
@Slf4j
@CrossOrigin
public class SystemController {

    @Value("${sa-token.public-key}")
    private String publicKey;

    @Value("${sa-token.private-key}")
    private String privateKey;

    @Autowired
    private UserService userService;

    @Autowired
    private SystemService systemService;

    @PostMapping("/initWebmasterInfo")
    public BaseReturnDto initWebmasterInfo(@RequestBody UserEntity userEntity) {
        String encodePassword = SaSecureUtil.rsaEncryptByPublic(publicKey, userEntity.getPassword());
        userEntity.setPassword(encodePassword);
        boolean initResult = userService.initWebmasterInfo(userEntity);
        BaseReturnDto result;
        if (initResult) {
            result = BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "站长信息初始化完成...");
        } else {
            result = BaseReturnDto.error(ExceptionEnum.INIT_WEBMASTER_REPEAT);
        }
        return result;
    }

    @GetMapping("/webmasterInfo")
    public BaseReturnDto webmasterInfo() {
        BaseReturnDto result;
        List<UserEntity> userEntityList = userService.find(new UserEntity());
        if (userEntityList.size() != 1) {
            result = BaseReturnDto.error(ExceptionEnum.WEBMASTER_ERROR);
        } else {
            UserEntity userEntity = userEntityList.get(0);
            userEntity.setToken(null);
            userEntity.setPassword(null);
            result = BaseReturnDto.success(userEntity);
        }
        return result;
    }

    @GetMapping("/websiteInfo")
    public BaseReturnDto websiteInfo() {
        Map<String, Object> result = systemService.websiteInfo();
        return BaseReturnDto.success(result);
    }
}
