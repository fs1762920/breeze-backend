package cn.fan.breeze.entity.vo;

import lombok.Data;

@Data
public class CommentInfo {

    private String customIp;

    private String nickname;

    private String mail;

    private String homePath;

    private String content;

    private Integer srcId;

    private Integer targetId;

    private Integer commentId;

    private Integer blogId;
}
