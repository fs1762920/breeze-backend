package cn.fan.breeze.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * b_page
 * @author 
 */
@Data
public class PageEntity implements Serializable {
    private Integer pageId;

    /**
     * 页面编码
     */
    private String pageCode;

    /**
     * markdown文档 用于导出
     */
    private String markdownContent;

    /**
     * html内容  用于展示
     */
    private String htmlContent;

    private Date ctime;

    private Date mtime;

    private static final long serialVersionUID = 1L;
}