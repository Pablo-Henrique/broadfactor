package com.broadfactor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnpjDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4431595299723798512L;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @CNPJ
    private String cnpj;

    private String tipo;

    private String porte;

    private String nome;

    private String fantasia;

    private String abertura;

    private String natureza_juridica;
}
