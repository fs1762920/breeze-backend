package cn.fan.breeze.controller;

import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.entity.PhotoEntity;
import cn.fan.breeze.service.PhotoService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/photo")
@Slf4j
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping("/upload")
    public BaseReturnDto upload(@RequestParam("files") MultipartFile[] files) throws IOException {
        photoService.upload(files);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "上传成功!");
    }

    @GetMapping("/findByPage")
    public BaseReturnDto findByPage(Integer pageNum, Integer pageSize) {
        PageInfo<PhotoEntity> pageInfo = photoService.findByPage(pageNum, pageSize);
        return BaseReturnDto.success(pageInfo);
    }

    @GetMapping("/findOne")
    public BaseReturnDto findOne(Integer photoId) {
        PhotoEntity photoEntity = photoService.getById(photoId);
        return BaseReturnDto.success(photoEntity);
    }

    @GetMapping("/delete")
    public BaseReturnDto delete(Integer photoId) {
        photoService.deleteById(photoId);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "删除成功");
    }

    @GetMapping("/deleteBatch")
    public BaseReturnDto deleteBatch(Integer[] photoIds) {
        photoService.deleteBatch(photoIds);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "删除成功");
    }
}
