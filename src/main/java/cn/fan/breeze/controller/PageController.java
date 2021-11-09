package cn.fan.breeze.controller;

import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.entity.PageEntity;
import cn.fan.breeze.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page")
@Slf4j
@CrossOrigin
public class PageController {

    @Autowired
    private PageService pageService;

    @PostMapping("/save")
    public BaseReturnDto save(@RequestBody PageEntity pageEntity) {
        pageService.save(pageEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "更新成功");
    }

    @PostMapping("/find")
    public BaseReturnDto find(PageEntity pageEntity) {
        PageEntity result = pageService.find(pageEntity);
        return BaseReturnDto.success(result);
    }
}
