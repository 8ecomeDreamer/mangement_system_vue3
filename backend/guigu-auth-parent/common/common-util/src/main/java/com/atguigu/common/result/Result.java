package com.atguigu.common.result;


import lombok.Data;

@Data
public class Result <T>{

    private String code;
    private String msg;
    private T payload;


    // 返回数据
    protected static <T> Result<T> build(T payload) {
        Result<T> result = new Result<T>();
        if (payload != null)
            result.setPayload(payload);
        return result;
    }

    public static <T> Result<T> build(T payload, String code, String msg) {
        Result<T> result = build(payload);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> build(T payload, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(payload);
        result.setCode(resultCodeEnum.getCode());
        result.setMsg(resultCodeEnum.getMessage());
        return result;
    }

    public static<T> Result<T> ok(){
        return Result.ok(null);
    }

    /**
     * 操作成功
     * @param data  baseCategory1List
     * @param <T>
     * @return
     */
    public static<T> Result<T> ok(T data){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static<T> Result<T> fail(){
        return Result.fail(null);
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static<T> Result<T> fail(T data){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    public Result<T> message(String msg){
        this.setMsg(msg);
        return this;
    }

    public Result<T> code(String code){
        this.setCode(code);
        return this;
    }


}
