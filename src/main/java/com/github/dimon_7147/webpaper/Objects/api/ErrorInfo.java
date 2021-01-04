package com.github.dimon_7147.webpaper.Objects.api;

public class ErrorInfo {
    private int error_code;
    private String message;

    public ErrorInfo(int error_code, String message) {
        this.error_code = error_code;
        this.message = message;
    }

    public ErrorInfo() {
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
