package com.broadfactor.service.impl;

import com.broadfactor.model.Cnpj;
import com.broadfactor.repository.CnpjRepository;
import com.broadfactor.service.CnpjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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
    public Cnpj findByCnpj(String cnpj) {
        return repository.findByCnpj(cnpj);
    }

    @Override
    @Transactional
    public Cnpj consumerCnpj(String cnpj) {
        RestTemplate template = new RestTemplate();
        if (validatorCnpj(cnpj)) {
            return template.getForObject("https://receitaws.com.br/v1/cnpj/" + cnpj, Cnpj.class);
        }
        return null;
    }

    @Override
    public boolean validatorCnpj(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            return false;
        }
        return cnpj.length() == 14;
    }

    @Override
    @Transactional
    public Cnpj insert(Cnpj entity) {
        return entity == null ? null : repository.save(entity);
    }


}
