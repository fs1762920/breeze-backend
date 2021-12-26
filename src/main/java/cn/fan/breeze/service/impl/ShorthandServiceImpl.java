package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.ShorthandMapper;
import cn.fan.breeze.entity.ShorthandEntity;
import cn.fan.breeze.service.ShorthandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ShorthandServiceImpl implements ShorthandService {

    @Autowired
    private ShorthandMapper shorthandMapper;

    /**
     *
     * @param pageNum
     * @param pageSize
     * @param type  1 门户   2 后台
     * @return
     */
    @Override
    public PageInfo<ShorthandEntity> findByPage(Integer pageNum, Integer pageSize, int type) {
        PageHelper.startPage(pageNum, pageSize);
        List<ShorthandEntity> shorthandEntityList = shorthandMapper.selectBySelective(new ShorthandEntity());
        PageInfo<ShorthandEntity> pageInfo = new PageInfo<>(shorthandEntityList);
        if (type == 1) {
            for (ShorthandEntity shorthandEntity : pageInfo.getList()) {
                if (shorthandEntity.getHidden() == 2) {
                    shorthandEntity.setContent(null);
                }
            }
        }
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ShorthandEntity shorthandEntity) {
        Date nowDate = new Date();
        shorthandEntity.setCtime(nowDate);
        shorthandEntity.setMtime(nowDate);
        shorthandEntity.setLeftOffset(Math.round((float) Math.random() * 4));
        shorthandEntity.setContentWidth(8 + Math.round((float) Math.random() * 8));
        shorthandMapper.insert(shorthandEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ShorthandEntity shorthandEntity) {
        Date nowDate = new Date();
        shorthandEntity.setMtime(nowDate);
        shorthandMapper.updateByPrimaryKeySelective(shorthandEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer shorthandId) {
        shorthandMapper.deleteByPrimaryKey(shorthandId);
    }

    @Override
    public ShorthandEntity findById(Integer shorthandId) {
        return shorthandMapper.selectByPrimaryKey(shorthandId);
    }
}
