package cn.fan.breeze.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@Slf4j
@CrossOrigin
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${upload.path-prefix}")
    private String pathPrefix;

    @SaCheckLogin
    @PostMapping("/upload")
    public BaseReturnDto upload(@RequestParam("file") MultipartFile file) throws IOException {
        return BaseReturnDto.success(pathPrefix + '/' + fileService.upload(file));
    }
}
