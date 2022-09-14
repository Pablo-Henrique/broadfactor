package com.broadfactor.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Data
@Builder
public class ErrorResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -6742734802738742010L;

    private Integer status;
    private String path;
    private String message;
    private String detail;

}
