package cn.fan.breeze.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * b_custom
 * @author 
 */
@Data
public class CustomEntity implements Serializable {
    private Integer customId;

    /**
     * 游客ip
     */
    private String customIp;

    /**
     * 头像链接（外链）
     */
    private String avatarPath;

    /**
     * 游客昵称
     */
    private String nickname;

    private String homePath;

    /**
     * 游客邮箱
     */
    private String mail;

    private Date lastVisitTime;

    private Date ctime;

    private Date mtime;

    private static final long serialVersionUID = 1L;
}