package com.jansora.demo.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jansora.demo.infrastructure.exception.BaseAppException;

import java.io.Serializable;
import java.util.Objects;

/**
 * <Description>Spring Controller Standard Result <br>
 *
 * @author zhang.yangyuan <br>
 * @version 1.0 <br>
 * @see com.jansora <br>
 * @since <br>
 */
public class ResultDto<T> implements Serializable {

    /*
        default true.
        if error has reached, Assign false and give reason on this.errorCode && this.errorDesc
     */
    private boolean status = true;
    /*
        data should be assigned when status is true;
     */
    @JsonProperty("data")
    private T data = null;
    /*
     errorCode should be assigned when status is false;
     */
    private String errorCode;
    /*
     errorDesc  should be assigned when status is false;
     */
    private String errorDesc;

    public ResultDto() {

    }

    public ResultDto(Status status, T data, String errorCode, String errorDesc, BaseAppException e) {
        this.setStatus(Status.SUCCESS.equals(status));
        this.data = data;
        this.setErrorCode(errorCode);
        this.setErrorDesc(errorDesc);
        if (Objects.nonNull(e)) {
            this.setErrorCode(e.getErrorCode());
            this.setErrorDesc(e.getErrorDesc());
        }
    }

    /**
     * <Description> 成功的构造函数 <br>
     *
     * @param data 返回数据
     * @author zhang.yangyuan  2020/11/26 18:17:12 <br>
     */
    public ResultDto(T data) {
        this(Status.SUCCESS, data, null, null, null);
    }

    /**
     * <Description> 失败 <br>
     *
     * @param e BaseException
     * @author zhang.yangyuan  2020/11/26 18:17:12 <br>
     */
    public ResultDto(BaseAppException e) {
        this.setStatus(false);
        this.setErrorCode(e.getErrorCode());
        this.setErrorDesc(e.getErrorDesc());
    }

    /**
     * <Description> 失败 <br>
     *
     * @param errorCode 错误编码
     * @param errorDesc 错误注释
     * @author zhang.yangyuan  2020/11/26 18:17:12 <br>
     */
    public static <T> ResultDto<T> FAIL(String errorCode, String errorDesc) {
        return new ResultDto<>(Status.FAILED, null, errorCode, errorDesc, null);
    }

    public static <T> ResultDto<T> FAIL(BaseAppException e) {
        return new ResultDto<>(Status.FAILED, null, null, null, e);
    }

    /**
     * <Description> 成功的构造函数 <br>
     *
     * @author zhang.yangyuan  2020/11/26 18:17:12 <br>
     */
    public static <T> ResultDto<T> SUCCESS() {
        return new ResultDto<>(Status.SUCCESS, null, null, null, null);
    }

    /**
     * <Description> 成功的构造函数 <br>
     *
     * @author zhang.yangyuan  2020/11/26 18:17:12 <br>
     */
    public static <T> ResultDto<T> SUCCESS(T data) {
        return new ResultDto<>(Status.SUCCESS, data, null, null, null);
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public enum Status {
        SUCCESS, FAILED
    }


}
