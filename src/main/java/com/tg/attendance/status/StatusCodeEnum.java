package com.tg.attendance.status;

public enum StatusCodeEnum {
    PRESENT(1);
    private int statusCode;
    StatusCodeEnum(int statusCode) {
        this.statusCode = statusCode;
    }
    public int getStatusCode() {
        return this.statusCode;
    }
}
