package ru.ginatulin.core.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class FaultedToken implements Serializable {
    private String token;

    public FaultedToken(String token) {
        this.token = token;
    }
}
