package com.broadfactor.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public final class Response<Type> {

    private Type data;
    private Map<String, Object> errors;

    public Map<String, Object> getErrors() {
        if (errors == null) {
            this.errors = new HashMap<>();
        }
        return errors;
    }
}
