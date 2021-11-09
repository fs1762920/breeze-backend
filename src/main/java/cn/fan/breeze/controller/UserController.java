package cn.fan.breeze.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.constant.ExceptionEnum;
import cn.fan.breeze.entity.UserEntity;
import cn.fan.breeze.entity.vo.ChangePass;
import cn.fan.breeze.service.UserService;
import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Value("${sa-token.public-key}")
    private String publicKey;

    @Value("${sa-token.private-key}")
    private String privateKey;

    @Value("${sa-token.tokenPrefix}")
    private String tokenPrefix;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public BaseReturnDto login(@RequestBody UserEntity userEntity) {
        BaseReturnDto result;
        UserEntity queryParam = new UserEntity();
        queryParam.setUsername(userEntity.getUsername());
        List<UserEntity> queryResult = userService.find(queryParam);
        if (!queryResult.isEmpty()) {
            String encodePassword = queryResult.get(0).getPassword();
            String decodePassword = SaSecureUtil.rsaDecryptByPrivate(privateKey, encodePassword);
            if (decodePassword.equals(userEntity.getPassword())) {
                StpUtil.login(queryResult.get(0).getUserId());
                queryResult.get(0).setToken(StpUtil.getTokenInfo().getTokenValue());
                UserEntity loginUpdate = new UserEntity();
                loginUpdate.setUserId(queryResult.get(0).getUserId());
                loginUpdate.setLastLoginTime(new Date());
                userService.update(loginUpdate);
                result = BaseReturnDto.success(queryResult.get(0));
            } else {
                result = BaseReturnDto.error(ExceptionEnum.AUTH_FAIL_CODE);
            }
        } else {
            result = BaseReturnDto.error(ExceptionEnum.USER_NOT_FOUND);
        }
        return result;
    }


    @GetMapping("/logout")
    public BaseReturnDto logout(HttpServletRequest request) {
        log.info("token: {}", request.getHeader("satoken").replace(tokenPrefix, "").trim());
        if (StpUtil.isLogin()) {
            StpUtil.logoutByTokenValue(request.getHeader("satoken").replace(tokenPrefix, "").trim());
        }
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "注销成功");
    }

    @PostMapping("/update")
    public BaseReturnDto update(@RequestBody UserEntity userEntity) {
        if (!StringUtils.isEmpty(userEntity.getPassword())) {
            String encodePassword = SaSecureUtil.rsaEncryptByPublic(publicKey, userEntity.getPassword());
            userEntity.setPassword(encodePassword);
        }
        userService.update(userEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "修改成功");
    }

    @SaCheckLogin
    @PostMapping("/changePass")
    public BaseReturnDto changePass(@RequestBody ChangePass changePass) {
        int userId = StpUtil.getLoginIdAsInt();
        UserEntity userEntity = userService.getById(userId);
        String oldPassword = SaSecureUtil.rsaDecryptByPrivate(privateKey, userEntity.getPassword());
        log.info("旧密码: {}", oldPassword);
        if (!changePass.getOldPass().equals(oldPassword)) {
            return BaseReturnDto.error(ExceptionEnum.OLD_PASS_DIFFER_CODE);
        } else {
            String newPassword = SaSecureUtil.rsaEncryptByPublic(publicKey, changePass.getNewPass());
            UserEntity updateUser = new UserEntity();
            updateUser.setUserId(userId);
            updateUser.setPassword(newPassword);
            userService.update(updateUser);
        }
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "密码修改成功");
    }
}
