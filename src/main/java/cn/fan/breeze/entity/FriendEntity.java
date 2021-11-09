package cn.fan.breeze.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * b_friend
 * @author 
 */
@Data
public class FriendEntity implements Serializable {
    private Integer friendId;

    private String nickname;

    private String avatarPath;

    private String personalSign;

    private String homePage;

    private Date ctime;

    private Date mtime;

    private static final long serialVersionUID = 1L;
}