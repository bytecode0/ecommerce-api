package com.jtspringproject.JtSpringProject.controller;


public class JAlertResponse {
    private String title = "";
    private String message = "";

    // Constructor
    public JAlertResponse() {}
    
    public JAlertResponse(String title, String message) {
        this.title = title;
        this.message = message;
    }

    // Getters y Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

