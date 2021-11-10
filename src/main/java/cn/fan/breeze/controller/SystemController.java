package cn.fan.breeze.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.constant.ExceptionEnum;
import cn.fan.breeze.entity.UserEntity;
import cn.fan.breeze.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/statistics")
    public BaseReturnDto statistics() {
        //博客数量

        //评论数量

        //阅读量

        //建站时长

        //分类数量

        //标签数量

        //友链数量

        return BaseReturnDto.success(null);
    }
}
