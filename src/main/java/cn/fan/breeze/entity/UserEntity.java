package cn.fan.breeze.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * b_user
 * @author 
 */
@Data
public class UserEntity implements Serializable {
    private Integer userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像附件id
     */
    private String avatar;

    /**
     * 签名
     */
    private String personalSign;

    /**
     * github
     */
    private String github;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 个人首页链接
     */
    private String homePath;

    /**
     * 籍贯
     */
    private String hometown;

    private Date ctime;

    private Date mtime;

    /**
     * 上次登陆时间
     */
    private Date lastLoginTime;

    private String token;

    private static final long serialVersionUID = 1L;
}