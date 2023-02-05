package com.rmeunier.pizzeriaapp.shared;


import lombok.NoArgsConstructor;

public class GenericResponse {
    private String message;

    public GenericResponse() {

    }

    public GenericResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
