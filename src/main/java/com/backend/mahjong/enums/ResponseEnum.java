package com.backend.mahjong.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResponseEnum {
    SUCCESS(200, "成功"),
    FAILED(400, "失敗");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ResponseEnum findByCode(Integer code) {
        for (ResponseEnum res : values()) {
            if (res.getCode() == code) {
                return res;
            }
        }
        return null;
    }
}
