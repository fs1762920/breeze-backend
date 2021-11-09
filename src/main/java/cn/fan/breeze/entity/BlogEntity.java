package cn.fan.breeze.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * b_blog
 * @author 
 */
@Data
public class BlogEntity implements Serializable {
    private Integer blogId;

    private Integer classifyId;

    private Integer[] labelIds;

    private String title;

    /**
     * markdown文本
     */
    private String markdownContent;

    /**
     * html文本  用于页面展示
     */
    private String htmlContent;

    private Integer readingCount;

    private Integer commentCount;

    /**
     * 封面
     */
    private String cover;

    /**
     * 摘要
     */
    private String summary;

    private Date ctime;

    private Date mtime;

    /**
     * 是否置顶  0 否    1 是
     */
    private Integer topped;

    /**
     * 开启评论  0 开启  1 关闭
     */
    private Integer commented;

    private ClassifyEntity classifyEntity;

    private List<LabelEntity> labelEntityList;

    private static final long serialVersionUID = 1L;
}