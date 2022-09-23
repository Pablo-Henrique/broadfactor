package com.broadfactor.controller;

import com.broadfactor.dto.CnpjDTO;
import com.broadfactor.model.Cnpj;
import com.broadfactor.response.Response;
import com.broadfactor.service.CnpjService;
import com.broadfactor.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/cnpj")
public class CnpjController {

    @Autowired
    private CnpjService service;

    @PostMapping(path = "/register")
    public ResponseEntity<Response<CnpjDTO>> insert(@RequestBody @Valid CnpjDTO cnpjDTO) {
        Response<CnpjDTO> response = new Response<>();
        Cnpj cnpj = service.insert(ObjectMapperUtils.map(cnpjDTO, Cnpj.class));
        response.setData(ObjectMapperUtils.map(cnpj, CnpjDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/consult/{cnpj}")
    public ResponseEntity<Response<CnpjDTO>> findByCnpj(@PathVariable(name = "cnpj") String cnpj) {
        Response<CnpjDTO> response = new Response<>();
        response.setData(ObjectMapperUtils.map(service.findByCnpj(cnpj), CnpjDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
