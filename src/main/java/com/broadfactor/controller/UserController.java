package com.broadfactor.controller;

import com.broadfactor.dto.UserDTO;
import com.broadfactor.model.Cnpj;
import com.broadfactor.model.User;
import com.broadfactor.response.Response;
import com.broadfactor.service.CnpjService;
import com.broadfactor.service.UserService;
import com.broadfactor.util.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CnpjService cnpjService;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/insert")
    public ResponseEntity<Response<UserDTO>> insert(@RequestBody @Valid UserDTO dto) {
        Response<UserDTO> response = new Response<>();

        User user = mapper.map(dto, User.class);
        user.setPassword(BCrypt.passwordEncoder(user.getPassword()));
        Cnpj cnpj = cnpjService.consumerCnpj(dto.getCnpj());

        user.setCnpj(cnpjService.insert(cnpj));

        response.setData(mapper.map(userService.insert(user), UserDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
