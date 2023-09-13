package net.kkl.bookapp.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BookAppResponse implements Serializable {
    public String msg;
    public Object data;
    public Integer code;
    private long timestamp;

    public static BookAppResponse fail(String errorMsg) {
        return BookAppResponse.builder()
                .code(0)
                .msg(errorMsg)
                .data("******")
                .timestamp(System.currentTimeMillis()/1000)
                .build();
    }
    public static BookAppResponse success(String msg, Object data) {
        return BookAppResponse.builder()
                .code(Constant.SUCCESS_CODE)
                .msg(msg)
                .data(data)
                .timestamp(System.currentTimeMillis()/1000)
                .build();
    }
    public static BookAppResponse fail(String errorMsg, Object data) {
        return BookAppResponse.builder()
                .code(Constant.FAILURE_CODE)
                .msg(errorMsg)
                .data(data)
                .timestamp(System.currentTimeMillis()/1000)
                .build();
    }
}
