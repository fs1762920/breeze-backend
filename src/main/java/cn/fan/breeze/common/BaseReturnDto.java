package cn.fan.breeze.common;

import cn.fan.breeze.constant.ExceptionEnum;

import java.io.Serializable;

public class BaseReturnDto implements Serializable {

    private int code;
    private String msg;
    private Object data;

    public BaseReturnDto(){
        this.code=100;
    }

    public BaseReturnDto(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public BaseReturnDto(int code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public static BaseReturnDto success(Object data) {
        BaseReturnDto returnDto = new BaseReturnDto();
        returnDto.setMsg("success");
        returnDto.setData(data);
        return returnDto;
    }

    public static BaseReturnDto error(int code, String msg) {
        BaseReturnDto returnDto = new BaseReturnDto();
        returnDto.setCode(code);
        returnDto.setMsg(msg);
        returnDto.setData(null);
        return returnDto;
    }

    public static BaseReturnDto error(ExceptionEnum exceptionEnum) {
        BaseReturnDto returnDto = new BaseReturnDto();
        returnDto.setCode(exceptionEnum.getCode());
        returnDto.setMsg(exceptionEnum.getMsg());
        returnDto.setData(null);
        return returnDto;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}