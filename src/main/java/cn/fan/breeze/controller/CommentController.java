package cn.fan.breeze.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.constant.ExceptionEnum;
import cn.fan.breeze.entity.CommentEntity;
import cn.fan.breeze.service.CommentService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/save")
    public BaseReturnDto save(@RequestBody CommentEntity commentEntity) {
        BaseReturnDto result;
        boolean success = commentService.save(commentEntity);
        if (success) {
            result = BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "发表成功!");
        } else {
            result = BaseReturnDto.error(ExceptionEnum.ILLEGAL_PARAM_ERROR);
        }
        return result;
    }

    @GetMapping("/findByPage")
    public BaseReturnDto findByPage(Integer pageNum, Integer pageSize) {
        PageInfo<CommentEntity> pageInfo = commentService.findByPage(pageNum, pageSize);
        return BaseReturnDto.success(pageInfo);
    }

    @SaCheckLogin
    @GetMapping("/delete")
    public BaseReturnDto delete(Integer commentId) {
        commentService.delete(commentId);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "删除成功!");
    }
}
