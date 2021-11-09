package cn.fan.breeze.controller;

import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.entity.LabelEntity;
import cn.fan.breeze.service.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/label")
@Slf4j
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;

    @PostMapping("/save")
    public BaseReturnDto save(@RequestBody LabelEntity labelEntity) {
        labelService.save(labelEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "添加成功");
    }

    @PostMapping("/update")
    public BaseReturnDto update(@RequestBody LabelEntity labelEntity) {
        labelService.update(labelEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "更新成功");
    }

    @GetMapping("/find")
    public BaseReturnDto find() {
        List<LabelEntity> labelEntityList = labelService.find(new LabelEntity());
        return BaseReturnDto.success(labelEntityList);
    }

    @GetMapping("/delete")
    public BaseReturnDto delete(Integer labelId) {
        labelService.deleteById(labelId);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "删除成功");
    }
}
