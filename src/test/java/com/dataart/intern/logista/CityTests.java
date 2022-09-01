package com.dataart.intern.logista;

import com.dataart.intern.logista.security.JwtUtils;
import com.dataart.intern.logista.model.City;
import com.dataart.intern.logista.repository.CityRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Comparator;
import java.util.List;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CityRepository cityRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @Sql("/test.sql")
    public void getByIdTest() throws Exception {
        String token = jwtUtils.generateToken("adamW");
        assertNotNull(token);
        mockMvc.perform(MockMvcRequestBuilders.get("/city/{id}", 4)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.name").value("Kyiv"))
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(status().isOk());
    }

    @Test
    @Sql("/test.sql")
    public void getAllTest() throws Exception {
        String token = jwtUtils.generateToken("adamW");
        assertNotNull(token);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/city")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        City c1 = new City();
        City c2 = new City();
        c1.setId(2);
        c1.setName("Dnipro");
        c2.setId(4);
        c2.setName("Kyiv");

        String cities = mvcResult.getResponse().getContentAsString();
        List<City> cityList = List.of(c1, c2);

        Gson gson = new Gson();
        List<City> city = gson.fromJson(cities, new TypeToken<List<City>>() {}.getType());
        city.sort(Comparator.comparing(City::getId));

        assertEquals(city.get(0).getName(), cityList.get(0).getName());
    }

    @Test
    @Sql("/test.sql")
    public void createTest() throws Exception {
        String token = jwtUtils.generateToken("adamW");
        assertNotNull(token);
        mockMvc.perform(MockMvcRequestBuilders.post("/city")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .content(format("{\"name\": \"Lviv\"}")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lviv"));

        City city = cityRepository.findById(1).get();
        assertEquals(city.getName(), "Lviv");
    }

    @Test
    @Sql("/test.sql")
    public void deleteTest() throws Exception {
        String token = jwtUtils.generateToken("adamW");
        assertNotNull(token);
        mockMvc.perform(MockMvcRequestBuilders.delete("/city/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        assertTrue(cityRepository.findById(2).isEmpty());

    }

    @Test
    @Sql("/test.sql")
    public void updateTest() throws Exception {
        String token = jwtUtils.generateToken("adamW");
        assertNotNull(token);
        mockMvc.perform(MockMvcRequestBuilders.put("/city/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .content("{\"name\": \"DniprO\"}"))
                .andExpect(status().isOk());

        City city = cityRepository.findById(2).get();
        assertEquals(city.getName(), "DniprO");
    }
}
