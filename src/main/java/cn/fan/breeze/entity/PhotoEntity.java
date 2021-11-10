package cn.fan.breeze.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * b_photo
 * @author 
 */
@Data
public class PhotoEntity implements Serializable {
    private Integer photoId;

    private String originalName;

    private String displayName;

    private String photoPath;

    /**
     * 图片宽度
     */
    private Integer width;

    private Date mtime;

    private Date ctime;

    /**
     * 图片高度
     */
    private Integer height;

    /**
     * 图片大小  byte
     */
    private Long size;

    private String mimeType;

    private static final long serialVersionUID = 1L;
}