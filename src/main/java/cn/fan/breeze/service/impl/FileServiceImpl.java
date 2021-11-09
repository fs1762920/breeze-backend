package cn.fan.breeze.service.impl;

import cn.fan.breeze.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${upload.target-path}")
    private String targetPath;

    @Value("${upload.path-prefix}")
    private String pathPrefix;

    @Override
    public String upload(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();  // 文件名
        String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        String dealedFileName = UUID.randomUUID().toString().replace("-", "") + fileSuffix;
        File dest = new File(targetPath + '/' + dealedFileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        file.transferTo(dest);
        return pathPrefix + '/' + dealedFileName;
    }
}
