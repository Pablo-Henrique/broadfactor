package com.broadfactor.service;

import com.broadfactor.model.Cnpj;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class CnpjServiceTest {

    private static final String CNPJ = "13574594000196";
    private static final String URL = "https://receitaws.com.br/v1/cnpj/";
    private static final RestTemplate TEMPLATE = new RestTemplate();

    @Autowired
    private CnpjService service;

    @Test
    public void testConsumerCnpj() {
        ResponseEntity<Cnpj> response = TEMPLATE.getForEntity(URL + CNPJ, Cnpj.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Cnpj.class, response.getBody());
    }
}
