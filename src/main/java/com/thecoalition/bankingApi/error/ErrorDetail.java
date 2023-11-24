package com.thecoalition.bankingApi.error;

public class ErrorDetail {

    private int statusCode;
    private String message;

    public ErrorDetail() {
    }

    public ErrorDetail(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
