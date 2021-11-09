package cn.fan.breeze.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * b_label
 * @author 
 */
@Data
public class LabelEntity implements Serializable {
    private Integer labelId;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 标签描述
     */
    private String labelDescribe;

    private Date ctime;

    private Date mtime;

    private static final long serialVersionUID = 1L;
}