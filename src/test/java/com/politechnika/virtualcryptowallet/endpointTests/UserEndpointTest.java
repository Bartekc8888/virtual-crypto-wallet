package com.politechnika.virtualcryptowallet.endpointTests;

import com.google.gson.Gson;
import com.politechnika.virtualcryptowallet.dto.RegistrationUserDto;
import com.politechnika.virtualcryptowallet.endpoint.UserEndpoint;
import com.politechnika.virtualcryptowallet.security.SecurityUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserEndpoint.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserEndpointTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    WebApplicationContext wAC;

    @MockBean
    SecurityUserDetailsService securityUserDetailsService;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(wAC).build();
    }

    @Test
    public void registerUserTest() throws Exception {

        RegistrationUserDto registrationUserDto = new RegistrationUserDto();
        registrationUserDto.setFirstName("adminFirstName");
        registrationUserDto.setUsername("adminUsername");
        registrationUserDto.setPassword("adminPassword");
        registrationUserDto.setMatchingPassword("adminPassword");

        Gson gson = new Gson();
        String json = gson.toJson(registrationUserDto);

        mvc.perform(post("/api/user/register")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());
    }
}
