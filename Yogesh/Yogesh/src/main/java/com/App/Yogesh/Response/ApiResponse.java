package com.App.Yogesh.Response;

public class ApiResponse {

    private String message;
public ApiResponse(){};
    public ApiResponse(String message, boolean status) {
        super();
        this.message = message;
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private boolean status;
}
