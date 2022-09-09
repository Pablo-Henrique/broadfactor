package com.broadfactor.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public final class Response<Type> implements Serializable {

    @Serial
    private static final long serialVersionUID = -8374112162112593810L;

    private Type data;
    private Map<String, String> errors = new HashMap<>();

    public void setErrors(String field, String defaultMessage) {
        errors.put(field, defaultMessage);
    }
}
