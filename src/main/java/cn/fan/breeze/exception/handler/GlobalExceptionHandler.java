package cn.fan.breeze.exception.handler;

import cn.fan.breeze.common.BaseReturnDto;
import cn.fan.breeze.constant.ExceptionEnum;
import cn.fan.breeze.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public BaseReturnDto bizExceptionHandler(HttpServletRequest req, BizException e){
        log.error("发生业务异常！原因是：{}",e.getMsg());
        return BaseReturnDto.error(e.getCode(),e.getMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public BaseReturnDto exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return BaseReturnDto.error(ExceptionEnum.BODY_NOT_MATCH);
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public BaseReturnDto exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是:",e);
        return BaseReturnDto.error(ExceptionEnum.INTERNAL_SERVER_ERROR);
    }
}
