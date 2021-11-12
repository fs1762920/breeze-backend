package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.CustomMapper;
import cn.fan.breeze.entity.CustomEntity;
import cn.fan.breeze.service.CustomService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CustomServiceImpl implements CustomService {

    @Autowired
    private CustomMapper customMapper;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public CustomEntity findMaster() {
        CustomEntity param = new CustomEntity();
        param.setCustomType(1);
        return customMapper.selectBySelective(param).get(0);
    }

    @Override
    public CustomEntity findByIp(String customIpAddr) {
        CustomEntity result = null;
        CustomEntity param = new CustomEntity();
        param.setCustomIp(customIpAddr);
        List<CustomEntity> customEntityList = customMapper.selectBySelective(param);
        if (!customEntityList.isEmpty()) {
            result = customEntityList.get(0);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CustomEntity customEntity) {
        List<CustomEntity> customEntityList = customMapper.selectBySelective(customEntity);
        if (customEntityList.isEmpty()) {
            JSONObject avatarJson = restTemplate.getForObject("https://api.muxiaoguo.cn/api/sjtx?method=pc", JSONObject.class);
            if (avatarJson != null && avatarJson.getInteger("code") == 200) {
                String avatarPath = avatarJson.getJSONObject("data").getString("imgurl");
                customEntity.setAvatarPath(avatarPath);
            }
            Date nowDate = new Date();
            customEntity.setCustomType(0);
            customEntity.setCtime(nowDate);
            customEntity.setMtime(nowDate);
            customEntity.setLastVisitTime(nowDate);
            customMapper.insert(customEntity);
        }
    }
}
