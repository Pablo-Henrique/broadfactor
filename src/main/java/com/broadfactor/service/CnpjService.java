package com.broadfactor.service;

import com.broadfactor.model.Cnpj;

import java.util.List;

public interface CnpjService {

    List<Cnpj> findAll();

    Cnpj insert(Cnpj cnpj);
}
