package com.broadfactor.service;

import com.broadfactor.model.User;
import com.broadfactor.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class UserServiceTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Before
    public void setUp(){
        BDDMockito.given(repository.save(Mockito.any())).willReturn(new User());
    }

    @Test
    public void testInsert() {
        assertNotNull(service.insert(getUser()));
    }

    private User getUser(){
        User user = new User();
        user.setUsername("user test");
        user.setEmail("usertest@gmail.com");
        user.setPassword("12356");
        return user;
    }
}
