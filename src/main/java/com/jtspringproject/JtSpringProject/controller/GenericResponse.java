package com.jtspringproject.JtSpringProject.controller;


public class GenericResponse<T> {
    private T result;

    private Boolean status;

    private JAlertResponse alert = new JAlertResponse();

    // Constructor
    public GenericResponse() {}

    // Getters y Setters
    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public JAlertResponse getAlert() {
        return alert;
    }

    public void setAlert(JAlertResponse alert) {
        this.alert = alert;
    }
}
