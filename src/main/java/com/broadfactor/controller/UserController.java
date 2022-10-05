package com.broadfactor.controller;

import com.broadfactor.dto.UserDTO;
import com.broadfactor.model.Cnpj;
import com.broadfactor.model.User;
import com.broadfactor.response.Response;
import com.broadfactor.service.CnpjService;
import com.broadfactor.service.UserService;
import com.broadfactor.util.ObjectMapperUtils;
import com.broadfactor.util.PasswordCryptUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private CnpjService cnpjService;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<Response<UserDTO>> insert(@RequestBody @Valid UserDTO dto) {
        Response<UserDTO> response = new Response<>();

        User user = ObjectMapperUtils.map(dto, User.class);
        user.setPassword(PasswordCryptUtils.passwordEncoder(user.getPassword()));

        Cnpj cnpj = cnpjService.consumerCnpj(dto.getCnpj());
        user.setCnpj(cnpjService.insert(cnpj));

        response.setData(ObjectMapperUtils.map(userService.insert(user), UserDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<UserDTO>>> findAllUsers() {
        Response<List<UserDTO>> response = new Response<>();
        response.setData(ObjectMapperUtils.mapAll(userService.findAll(), UserDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
