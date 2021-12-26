package cn.fan.breeze.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * b_shorthand
 * @author 
 */
@Data
public class ShorthandEntity implements Serializable {
    private Integer shorthandId;

    private String content;

    /**
     * 1 公开  2 私密
     */
    private Integer hidden;

    private Date ctime;

    private Date mtime;

    private Integer leftOffset;

    private Integer contentWidth;

    private Integer userId;

    private UserEntity userEntity;

    private static final long serialVersionUID = 1L;
}