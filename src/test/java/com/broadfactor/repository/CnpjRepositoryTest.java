package com.broadfactor.repository;

import com.broadfactor.model.Cnpj;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(profiles = "test")
public class CnpjRepositoryTest {

    @Autowired
    private CnpjRepository repository;

    @Test
    public void testInsert(){
        Cnpj cnpj = new Cnpj();
        cnpj.setAbertura("19/04/2011");
        cnpj.setFantasia("MCDONALDS");
        cnpj.setNome("QSC ALIMENTOS E COMERCIO LTDA");
        cnpj.setPorte("DEMAIS");
        cnpj.setNatureza_juridica("206-2 - Sociedade Empresária Limitada");
        cnpj.setTipo("MATRIZ");
        repository.save(cnpj);
    }
}
