package com.broadfactor.service;

import com.broadfactor.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void testInsert() {
        assertNotNull(service.insert(getUser()));
    }

    @Test
    public void testFindAll() {
        assertNotNull(service.findAll());
    }

    private User getUser() {
        User user = new User();
        user.setUsername("user test");
        user.setEmail("usertest@gmail.com");
        user.setPassword("12356");
        return user;
    }
}
