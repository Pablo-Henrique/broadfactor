package com.broadfactor.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public final class Response<Type> implements Serializable {

    @Serial
    private static final long serialVersionUID = -8374112162112593810L;

    private Type data;
    private List<String> errors;

    public List<String> getErrors() {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        return errors;
    }
}
