package cn.fan.breeze.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChangePass implements Serializable {

    private String oldPass;

    private String newPass;

    private String checkPass;
}
