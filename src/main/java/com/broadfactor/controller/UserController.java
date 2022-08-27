package com.broadfactor.controller;

import com.broadfactor.dto.UserDTO;
import com.broadfactor.model.User;
import com.broadfactor.response.Response;
import com.broadfactor.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    private UserService service;

    @PostMapping(path = "/insert")
    public ResponseEntity<Response<UserDTO>> insert(@RequestBody @Valid UserDTO dto, BindingResult result) {
        Response<UserDTO> response = new Response<>();
        if (result.hasErrors()) {
            response.setErrors(result.getModel());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        User user = service.insert(mapper.map(dto, User.class));
        response.setData(mapper.map(user, UserDTO.class));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
