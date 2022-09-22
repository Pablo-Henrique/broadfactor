package com.broadfactor.controller;

import com.broadfactor.dto.CnpjDTO;
import com.broadfactor.model.Cnpj;
import com.broadfactor.response.Response;
import com.broadfactor.service.CnpjService;
import com.broadfactor.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/cnpj")
public class CnpjController {

    @Autowired
    private CnpjService service;

    @PostMapping(path = "/insert")
    public ResponseEntity<Response<CnpjDTO>> insert(@RequestBody @Valid CnpjDTO cnpjDTO) {
        Response<CnpjDTO> response = new Response<>();
        Cnpj cnpj = service.insert(ObjectMapperUtils.map(cnpjDTO, Cnpj.class));
        response.setData(ObjectMapperUtils.map(cnpj, CnpjDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
