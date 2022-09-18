package com.broadfactor.repository;

import com.broadfactor.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(profiles = "test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("User Test");
        user.setEmail("test@email.com");
        user.setPassword("123456");

        User response = repository.save(user);
        assertNotNull(response);
    }

    @Test
    public void testFindAll() {
        List<User> users = repository.findAll();
        assertNotNull(users);
    }

}
