package com.broadfactor.repository;

import com.broadfactor.model.Cnpj;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(profiles = "test")
public class CnpjRepositoryTest {

    private static final String CNPJ = "22.932.687/0007-21";

    @Autowired
    private CnpjRepository repository;

    @Before
    public void setUp() {
        assertNotNull(repository.save(getCnpj()));
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testFindByCnpj() {
        Cnpj cnpj = repository.findByCnpj(CNPJ).get();
        assertNotNull(cnpj);
        assertEquals(cnpj.getCnpj(), CNPJ);
    }

    private Cnpj getCnpj() {
        Cnpj cnpj = new Cnpj();
        cnpj.setAbertura("19/04/2011");
        cnpj.setCnpj(CNPJ);
        cnpj.setFantasia("MCDONALDS");
        cnpj.setNome("QSC ALIMENTOS E COMERCIO LTDA");
        cnpj.setPorte("DEMAIS");
        cnpj.setNatureza_juridica("206-2 - Sociedade Empresária Limitada");
        cnpj.setTipo("MATRIZ");
        return cnpj;
    }
}
