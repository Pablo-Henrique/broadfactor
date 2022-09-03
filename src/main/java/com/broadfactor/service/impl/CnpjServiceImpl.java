package com.broadfactor.service.impl;

import com.broadfactor.model.Cnpj;
import com.broadfactor.repository.CnpjRepository;
import com.broadfactor.service.CnpjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CnpjServiceImpl implements CnpjService {

    @Autowired
    private CnpjRepository repository;

    @Override
    public List<Cnpj> findAll() {
        return repository.findAll();
    }

    @Override
    public Cnpj insert(Cnpj cnpj) {
        return repository.save(cnpj);
    }
}
