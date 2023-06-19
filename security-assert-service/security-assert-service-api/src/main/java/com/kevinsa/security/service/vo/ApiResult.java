package com.kevinsa.security.service.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ApiResult<T> extends Response<Collection<T>> {

    public ApiResult(Collection<T> data) {
        super(data);
    }

    public ApiResult() {

    }

    public static <T> ApiResult<T> buildSuccess() {
        ApiResult<T> result = new ApiResult<T>();
        result.setResult(1);
        result.setErrorMsg("success");
        return result;
    }


    public static <T> ApiResult<T> buildFailure(int errorCode, String errorMsg) {
        ApiResult<T> result = new ApiResult<T>();
        result.setResult(errorCode);
        result.setErrorMsg(errorMsg);
        return result;
    }
}
