package cn.fan.breeze.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.entity.FriendEntity;
import cn.fan.breeze.service.FriendService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
@CrossOrigin
@Slf4j
public class FriendController {

    @Autowired
    private FriendService friendService;

    @SaCheckLogin
    @PostMapping("/save")
    public BaseReturnDto save(@RequestBody FriendEntity friendEntity) {
        friendService.save(friendEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "添加成功");
    }

    @SaCheckLogin
    @PostMapping("/update")
    public BaseReturnDto update(@RequestBody FriendEntity friendEntity) {
        friendService.update(friendEntity);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "更新成功");
    }

    @SaCheckLogin
    @GetMapping("/findByPage")
    public BaseReturnDto findByPage(Integer pageNum, Integer pageSize) {
        PageInfo<FriendEntity> pageInfo = friendService.findByPage(pageNum, pageSize);
        return BaseReturnDto.success(pageInfo);
    }

    @GetMapping("/find")
    public BaseReturnDto find() {
        List<FriendEntity> friendEntityList = friendService.find();
        return BaseReturnDto.success(friendEntityList);
    }

    @SaCheckLogin
    @GetMapping("/delete")
    public BaseReturnDto delete(Integer friendId) {
        friendService.deleteById(friendId);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "删除成功!");
    }
}
