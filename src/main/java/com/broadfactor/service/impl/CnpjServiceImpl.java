package com.broadfactor.service.impl;

import com.broadfactor.handler.exceptions.EntityNotFoundException;
import com.broadfactor.model.Cnpj;
import com.broadfactor.repository.CnpjRepository;
import com.broadfactor.service.CnpjService;
import com.broadfactor.util.CnpjUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
public class CnpjServiceImpl implements CnpjService {

    @Autowired
    private CnpjRepository repository;

    @Override
    public Cnpj insert(Cnpj entity) {
        if (entity != null && repository.findByCnpj(entity.getCnpj()).isPresent()) {
            throw new DataIntegrityViolationException("CNPJ já cadastrado!");
        }
        return entity == null ? null : repository.save(entity);
    }

    @Override
    public List<Cnpj> findAll() {
        return repository.findAll();
    }

    @Override
    public Cnpj findByCnpj(String cnpj) {
        return repository.findByCnpj(CnpjUtils.maskFormatterToCnpj(cnpj)).orElseThrow(() -> new EntityNotFoundException("Cnpj não encontrado!"));
    }

    @Override
    public Cnpj consumerCnpj(String cnpj) {
        RestTemplate template = new RestTemplate();
        if (CnpjUtils.validatorCnpj(cnpj)) {
            return template.getForObject("https://receitaws.com.br/v1/cnpj/" + cnpj, Cnpj.class);
        }
        return null;
    }
}
