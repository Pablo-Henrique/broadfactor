package com.broadfactor.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public final class Response<Type> {

    private Type data;
    private List<String> errors;

    public List<String> getErrors() {
        if (errors == null) {
            this.errors = new ArrayList<>();
        }
        return errors;
    }
}
