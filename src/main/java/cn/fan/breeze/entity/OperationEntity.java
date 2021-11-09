package cn.fan.breeze.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * b_operation
 * @author 
 */
@Data
public class OperationEntity implements Serializable {
    private Integer operationId;

    /**
     * 操作内容
     */
    private String action;

    /**
     * 关联对象(文章/照片:作者;评论（操作）:管理员)
     */
    private String relevance;

    private Date ctime;

    private Date mtime;

    private static final long serialVersionUID = 1L;
}