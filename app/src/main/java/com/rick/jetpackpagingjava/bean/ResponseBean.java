package com.rick.jetpackpagingjava.bean;

import java.io.Serializable;

public class ResponseBean<T> implements Serializable {
    public ResponseBean(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.items = data;
    }

    public int code;
    public String msg;
    public T items;
}
