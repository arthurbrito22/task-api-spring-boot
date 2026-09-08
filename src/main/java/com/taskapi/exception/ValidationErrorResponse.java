package com.taskapi.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorResponse {
    private LocalDateTime dataHora = LocalDateTime.now();
    private int status;
    private Map<String, String> errors;

    public ValidationErrorResponse() {

    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public ValidationErrorResponse(int status, Map<String, String> errors) {
        this.status = status;
        this.errors = errors;
    }
}
