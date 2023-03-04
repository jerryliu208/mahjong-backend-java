package com.backend.mahjong.data.response;

import com.backend.mahjong.enums.ResponseEnum;
import lombok.Data;

@Data
public class ResponseData {
    private Integer code;
    private String msg;
    private Object data;

    public ResponseData(){
        this.code = ResponseEnum.SUCCESS.getCode();
        this.msg = ResponseEnum.SUCCESS.getMsg();
        //this.data = null;
    }

    public ResponseData(Integer code){
        ResponseEnum responseEnum = ResponseEnum.findByCode(code);
        if(responseEnum != null) {
            switch (responseEnum) {
                case SUCCESS:
                    this.code = ResponseEnum.SUCCESS.getCode();
                    this.msg = ResponseEnum.SUCCESS.getMsg();
                    break;
                case FAILED:
                    this.code = ResponseEnum.FAILED.getCode();
                    this.msg = ResponseEnum.FAILED.getMsg();
                    break;
            }
        }
    }

    public ResponseData(Object data){
        this.code = ResponseEnum.SUCCESS.getCode();
        this.msg = ResponseEnum.SUCCESS.getMsg();
        this.data = data;
    }
    public ResponseData(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResponseData(Integer code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
