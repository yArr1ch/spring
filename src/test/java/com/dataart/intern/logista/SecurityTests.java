package com.dataart.intern.logista;

import com.dataart.intern.logista.security.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    public void permitAllTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/depot")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void invalidTokenTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/city")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer rghjgklvav"))
                .andExpect(status().isForbidden());
    }

    @Test
    @Sql("/test.sql")
    public void invalidRoleTest() throws Exception {
        String token = jwtUtils.generateToken("Oleg233");
        assertNotNull(token);
        mockMvc.perform(MockMvcRequestBuilders.delete("/city/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    @Test
    @Sql("/test.sql")
    public void validTokenTest() throws Exception {
        String token = jwtUtils.generateToken("Oleg233");
        assertNotNull(token);
        mockMvc.perform(MockMvcRequestBuilders.get("/parcel")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    @Sql("/test.sql")
    public void validRoleTest() throws Exception {
        String token = jwtUtils.generateToken("Oleg233");
        assertNotNull(token);
        mockMvc.perform(MockMvcRequestBuilders.get("/city/{id}", 4)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}
