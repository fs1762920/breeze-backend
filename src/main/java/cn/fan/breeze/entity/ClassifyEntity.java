package cn.fan.breeze.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * b_classify
 * @author 
 */
@Data
public class ClassifyEntity implements Serializable {
    private Integer classifyId;

    /**
     * 分类名称
     */
    private String classifyName;

    /**
     * 分类描述
     */
    private String classifyDescribe;

    private Date ctime;

    private Date mtime;

    /**
     * 是否启用   0 禁用    1 启用
     */
    private Integer enabled;

    private Integer blogCount;

    private static final long serialVersionUID = 1L;
}