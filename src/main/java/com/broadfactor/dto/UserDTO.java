package com.broadfactor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4991871408064598402L;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    @Email(message = "Insira um email valido!")
    private String email;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Length(min = 6, message = "Insira o minimo de 6 caracteres para senha")
    private String password;

}
