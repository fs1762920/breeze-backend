package cn.fan.breeze.service.impl;

import cn.fan.breeze.dao.*;
import cn.fan.breeze.entity.ClassifyEntity;
import cn.fan.breeze.entity.UserEntity;
import cn.fan.breeze.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Service
@Slf4j
public class SystemServiceImpl implements SystemService {

    public static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private ClassifyMapper classifyMapper;

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public Map<String, Object> websiteInfo() {
        Map<String, Object> result = new HashMap<>();

        CompletableFuture<String> blogCountFuture = CompletableFuture.supplyAsync(() -> {
            Integer blogCount = blogMapper.getCount();
            result.put("blogCount", blogCount);
            return "success";
        });

        CompletableFuture<String> commentCountFuture = CompletableFuture.supplyAsync(() -> {
            //评论数量
            Integer commentCount = commentMapper.getCount();
            Integer replyCount = replyMapper.getCount();
            result.put("commentCount", commentCount + replyCount);
            return "success";
        });

        CompletableFuture<String> readingCountFuture = CompletableFuture.supplyAsync(() -> {
            //阅读量
            Integer readingCount = blogMapper.getReadingCount();
            result.put("readingCount", readingCount);
            return "success";
        });

        CompletableFuture<String> buildDurationFuture = CompletableFuture.supplyAsync(() -> {
            //建站时长
            List<UserEntity> userEntityList = userMapper.selectBySelective(new UserEntity());
            if (userEntityList.size() == 1) {
                Date buildTime = userEntityList.get(0).getCtime();
                result.put("buildTime", format.format(buildTime));
                long currentMillion = new Date().getTime();
                long buildDurationMillion = currentMillion - buildTime.getTime();
                long buildDuration = buildDurationMillion / (1000 * 60 * 60 * 24);
                result.put("buildDuration", buildDuration);
            }
            return "success";
        });

        CompletableFuture<String> classifyCountFuture = CompletableFuture.supplyAsync(() -> {
            //分类数量
            Integer classifyCount = classifyMapper.getCount();
            result.put("classifyCount", classifyCount);
            return "success";
        });

        CompletableFuture<String> labelCountFuture = CompletableFuture.supplyAsync(() -> {
            //标签数量
            Integer labelCount = labelMapper.getCount();
            result.put("labelCount", labelCount);
            return "success";
        });

        CompletableFuture<String> friendCountFuture = CompletableFuture.supplyAsync(() -> {
            //友链数量
            Integer friendCount = friendMapper.getCount();
            result.put("friendCount", friendCount);
            return "success";
        });

        CompletableFuture<Void> allFuture = CompletableFuture.allOf(blogCountFuture, commentCountFuture, readingCountFuture,
                buildDurationFuture, classifyCountFuture, labelCountFuture, friendCountFuture);
        allFuture.join();
        return result;
    }
}
