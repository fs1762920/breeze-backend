package cn.fan.breeze.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.entity.MusicEntity;
import cn.fan.breeze.service.MusicService;
import com.github.pagehelper.PageInfo;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/music")
@CrossOrigin
@Slf4j
public class MusicController {
    
    @Autowired
    private MusicService musicService;

    @SaCheckLogin
    @PostMapping("/save")
    public BaseReturnDto save(@RequestParam("file") MultipartFile file, @RequestParam(required = false) String lyric) throws InvalidDataException, IOException, UnsupportedTagException {
        musicService.save(file, lyric);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "上传成功");
    }

    @SaCheckLogin
    @GetMapping("/delete")
    public BaseReturnDto delete(Integer musicId) {
        musicService.delete(musicId);
        return BaseReturnDto.success(BaseReturnDto.RESP_SUCCESS_CODE, "删除成功");
    }

    @GetMapping("/find")
    public BaseReturnDto find() {
        List<MusicEntity> musicEntityList = musicService.findAll();
        return BaseReturnDto.success(musicEntityList);
    }

    @SaCheckLogin
    @GetMapping("/findByPage")
    public BaseReturnDto findByPage(Integer pageNum, Integer pageSize) {
        PageInfo<MusicEntity> pageInfo = musicService.findByPage(pageNum, pageSize);
        return BaseReturnDto.success(pageInfo);
    }
}
