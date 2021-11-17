package cn.fan.breeze.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * b_comment
 * @author 
 */
@Data
public class CommentEntity implements Serializable {
    
    private Integer commentId;

    /**
     * 评论者id
     */
    private Integer srcId;

    private String srcNickname;

    private String srcAvatarPath;

    private String srcMail;

    private String srcIp;

    /**
     * 评论内容
     */
    private String content;

    /**
     * -1 关于页面   >=1 博客id
     */
    private Integer blogId;

    /**
     * 0 不可用   1 可用
     */
    private Integer isValid;

    private Date ctime;

    private Date mtime;

    private List<ReplyEntity> replyEntityList;

    private static final long serialVersionUID = 1L;
}