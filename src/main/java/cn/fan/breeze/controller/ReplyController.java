package cn.fan.breeze.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.constant.ExceptionEnum;
import cn.fan.breeze.entity.ReplyEntity;
import cn.fan.breeze.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reply")
@CrossOrigin
@Slf4j
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @SaCheckLogin
    @PostMapping("/save")
    public BaseReturnDto save(@RequestBody ReplyEntity replyEntity) {
        BaseReturnDto result;
        boolean success = replyService.save(replyEntity);
        if (success) {
            result = BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "回复成功!");
        } else {
            result = BaseReturnDto.error(ExceptionEnum.ILLEGAL_PARAM_ERROR);
        }
        return result;
    }

    @SaCheckLogin
    @GetMapping("/delete")
    public BaseReturnDto delete(Integer replyId) {
        replyService.delete(replyId);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "删除成功!");
    }
}
