package com.broadfactor.controller;

import com.broadfactor.dto.UserDTO;
import com.broadfactor.model.User;
import com.broadfactor.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class UserControllerTest {

    private final static Long ID = 1L;
    private final static String USERNAME = "Test User";
    private final static String EMAIL = "user@test.com";
    private final static String PASSWORD = "123456";
    private final static String URL = "/user";

    @MockBean
    private UserService service;

    @Autowired
    private MockMvc mock;

    @Test
    public void testInsert() throws Exception {

        BDDMockito.given(service.insert(Mockito.any(User.class))).willReturn(getMockUser());

        mock.perform(MockMvcRequestBuilders.post(URL + "/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonPayload(ID, USERNAME, EMAIL, PASSWORD)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.username").value(USERNAME))
                .andExpect(jsonPath("$.data.email").value(EMAIL))
                .andExpect(jsonPath("$.data.password").doesNotExist());
    }

    @Test
    public void testInvalidInsertUser() throws Exception {

        BDDMockito.given(service.insert(Mockito.any(User.class))).willReturn(getMockUser());

        mock.perform(MockMvcRequestBuilders.post(URL + "/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonPayload(ID, USERNAME, EMAIL, "1234")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("Senha deve ter mais de que 6 caracteres."))
                .andReturn();

    }


    private String jsonPayload(Long id, String username, String email, String password) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setPassword(password);

        mapper.disable(MapperFeature.USE_ANNOTATIONS);
        return mapper.writeValueAsString(userDTO);
    }

    private User getMockUser() {
        User user = new User();
        user.setId(ID);
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        return user;
    }


}
