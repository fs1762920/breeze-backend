package cn.fan.breeze.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * b_reply
 * @author 
 */
@Data
public class ReplyEntity implements Serializable {
    private Integer replyId;

    private Integer srcId;

    private String srcNickname;

    private String srcAvatarPath;

    private String srcMail;

    private String srcIp;

    private Integer targetId;

    private String targetNickname;

    private String targetAvatarPath;

    private String targetMail;

    private String targetIp;

    private String content;

    private Date ctime;

    private Date mtime;

    /**
     * 0不可用   1可用
     */
    private Integer isValid;

    private Integer commentId;

    private Integer blogId;

    private static final long serialVersionUID = 1L;
}