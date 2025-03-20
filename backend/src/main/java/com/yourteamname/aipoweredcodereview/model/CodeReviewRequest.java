package com.yourteamname.aipoweredcodereview.model;

public class CodeReviewRequest {
    private String code;

    public CodeReviewRequest() {
    }

    public CodeReviewRequest(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}