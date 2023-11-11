package com.atguigu.system.config;

import com.atguigu.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**全局异常 和特定异常
     * 如果ExceptionHandler 参数为Exception代表所有异常都会触发 如果为其他触发则代表只有触发那个异常才会提示
     * 总结：谁的范围小触发谁
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail().message("这里是全局异常");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.fail().message("这里是特定异常");
    }


    /**
     * 自定义异常 可与try catch搭配使用
     */
    @ExceptionHandler(SelfException.class)
    @ResponseBody
    public Result error(SelfException e){
        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }
}
