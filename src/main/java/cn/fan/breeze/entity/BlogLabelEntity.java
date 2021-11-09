package cn.fan.breeze.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * b_blog_label
 * @author 
 */
@Data
public class BlogLabelEntity implements Serializable {
    private Integer blId;

    private Integer blogId;

    private Integer labelId;

    private static final long serialVersionUID = 1L;
}