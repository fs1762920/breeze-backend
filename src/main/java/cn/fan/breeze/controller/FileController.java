package cn.fan.breeze.controller;

import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public BaseReturnDto uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
        return BaseReturnDto.success(fileService.upload(file));
    }
}
