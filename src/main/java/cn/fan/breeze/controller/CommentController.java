package cn.fan.breeze.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.constant.ExceptionEnum;
import cn.fan.breeze.entity.CommentEntity;
import cn.fan.breeze.entity.vo.CommentInfo;
import cn.fan.breeze.service.CommentService;
import cn.fan.breeze.utils.HttpUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/comment")
@CrossOrigin
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/save")
    public BaseReturnDto save(@RequestBody CommentInfo commentInfo, HttpServletRequest request) {
        BaseReturnDto result;
        String ipAddr = HttpUtils.getIpAddr(request);
        commentInfo.setCustomIp(ipAddr);
        boolean success = commentService.save(commentInfo);
        if (success) {
            result = BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "发表成功!");
        } else {
            result = BaseReturnDto.error(ExceptionEnum.ILLEGAL_PARAM_ERROR);
        }
        return result;
    }

    @SaCheckLogin
    @GetMapping("/findByPageBackend")
    public BaseReturnDto findByPageBackend(Integer type, Integer pageNum, Integer pageSize) {
        PageInfo<CommentEntity> pageInfo = commentService.findByPageBackend(type, pageNum, pageSize);
        return BaseReturnDto.success(pageInfo);
    }


    @GetMapping("/findByPage")
    public BaseReturnDto findByPage(CommentEntity commentEntity, Integer pageNum, Integer pageSize) {
        PageInfo<CommentEntity> pageInfo = commentService.findByPage(commentEntity, pageNum, pageSize);
        return BaseReturnDto.success(pageInfo);
    }

    @SaCheckLogin
    @GetMapping("/delete")
    public BaseReturnDto delete(Integer commentId) {
        commentService.delete(commentId);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "删除成功!");
    }
}
