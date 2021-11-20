package cn.fan.breeze.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * b_music
 * @author 
 */
@Data
public class MusicEntity implements Serializable {
    private Integer musicId;

    private String title;

    private String album;

    private String artist;

    private String lyric;

    private String src;

    private Long duration;

    private Date ctime;

    private Date mtime;

    private Long size;

    private String albumPicture;

    private static final long serialVersionUID = 1L;
}