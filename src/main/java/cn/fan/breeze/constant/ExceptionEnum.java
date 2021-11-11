package cn.fan.breeze.constant;

public enum ExceptionEnum {

    SUCCESS(200, "成功!"),
    BODY_NOT_MATCH(400,"请求的数据格式不符!"),
    AUTH_FAIL_CODE(401,"密码错误!"),

    OLD_PASS_DIFFER_CODE(406,"旧密码错误!"),

    USER_NOT_FOUND(402,"该用户不存在!"),
    IDENTITY_FAIL_CODE(403, "没有权限!"),
    RESOURCE_NOT_FOUND(404, "未找到该资源!"),

    NO_TOKEN_CODE(405, "请重新登录!"),
    DATA_REPEAT_CODE(407, "数据已存在!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503,"服务器正忙，请稍后再试!"),
    WEBMASTER_ERROR(504,"获取博主信息失败!"),


    INIT_WEBMASTER_REPEAT(506, "站长信息重复初始化!"),
    INIT_WEBSITE_REPEAT(507, "网站信息重复初始化!");

    /** 错误码 */
    private int code;

    /** 错误描述 */
    private String msg;

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
