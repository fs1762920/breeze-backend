package cn.fan.breeze.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.entity.BlogEntity;
import cn.fan.breeze.service.BlogService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
@Slf4j
@CrossOrigin
public class BlogController {

    @Autowired
    private BlogService blogService;

    @SaCheckLogin
    @PostMapping("/save")
    public BaseReturnDto save(@RequestBody BlogEntity blogEntity) {
        blogService.save(blogEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "发布成功");
    }

    /**
     * 永久删除
     * @param blogId
     * @return
     */
    @SaCheckLogin
    @GetMapping("/delete")
    public BaseReturnDto delete(Integer blogId) {
        blogService.deleteById(blogId);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "删除成功");
    }

    /**
     * 后台分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @SaCheckLogin
    @GetMapping("/findByPageBackend")
    public BaseReturnDto findByPageBackend(Integer pageNum, Integer pageSize) {
        PageInfo<BlogEntity> pageInfo = blogService.findByPage(new BlogEntity(), pageNum, pageSize);
        return BaseReturnDto.success(pageInfo);
    }

    /**
     * 门户分页查询
     * @param blogEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findByPage")
    public BaseReturnDto findByPage(BlogEntity blogEntity, Integer pageNum, Integer pageSize) {
        if(blogEntity == null) {
            blogEntity = new BlogEntity();
        }
        blogEntity.setStatus(1);
        PageInfo<BlogEntity> pageInfo = blogService.findByPage(blogEntity, pageNum, pageSize);
        return BaseReturnDto.success(pageInfo);
    }

    @SaCheckLogin
    @PostMapping("/update")
    public BaseReturnDto update(@RequestBody BlogEntity blogEntity) {
        blogService.update(blogEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "发布成功");
    }

    @GetMapping("/findOne")
    public BaseReturnDto findOne(Integer blogId) {
        BlogEntity blogEntity = blogService.findById(blogId);
        return BaseReturnDto.success(blogEntity);
    }

    /**
     * 移入回收站
     * @param blogId
     * @return
     */
    @SaCheckLogin
    @GetMapping("/move")
    public BaseReturnDto move(Integer blogId) {
        BlogEntity param = new BlogEntity();
        param.setBlogId(blogId);
        param.setStatus(2);
        blogService.move(param);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "已成功移入回收站!");
    }


    @SaCheckLogin
    @GetMapping("/publish")
    public BaseReturnDto publish(Integer blogId) {
        BlogEntity param = new BlogEntity();
        param.setBlogId(blogId);
        param.setStatus(1);
        blogService.publish(param);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "发布成功!");
    }
}
