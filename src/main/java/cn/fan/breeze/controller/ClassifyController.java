package cn.fan.breeze.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.entity.ClassifyEntity;
import cn.fan.breeze.service.ClassifyService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classify")
@Slf4j
@CrossOrigin
public class ClassifyController {

    @Autowired
    private ClassifyService classifyService;

    @SaCheckLogin
    @PostMapping("/save")
    public BaseReturnDto save(@RequestBody ClassifyEntity classifyEntity) {
        classifyService.save(classifyEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "添加成功");
    }

    @SaCheckLogin
    @PostMapping("/update")
    public BaseReturnDto update(@RequestBody ClassifyEntity classifyEntity) {
        classifyService.update(classifyEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "更新成功");
    }

    @SaCheckLogin
    @GetMapping("/delete")
    public BaseReturnDto delete(Integer classifyId) {
        classifyService.deleteById(classifyId);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "删除成功");
    }

    @GetMapping("/findByPage")
    public BaseReturnDto findByPage(Integer pageNum, Integer pageSize) {
        PageInfo<ClassifyEntity> pageInfo = classifyService.findByPage(new ClassifyEntity(), pageNum, pageSize);
        return BaseReturnDto.success(pageInfo);
    }

    @GetMapping("/find")
    public BaseReturnDto find() {
        List<ClassifyEntity> classifyEntityList = classifyService.find(new ClassifyEntity());
        return BaseReturnDto.success(classifyEntityList);
    }
}
