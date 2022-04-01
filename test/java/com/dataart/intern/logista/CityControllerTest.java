package com.dataart.intern.logista;

import com.dataart.intern.logista.controller.CityController;

import com.dataart.intern.logista.model.City;
import com.dataart.intern.logista.service.CityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.*;


@WebMvcTest(CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CityService cityService;

    @Test
    public void testCreate() throws Exception {

        when(cityService.create(any(City.class))).thenReturn(1);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/city/create")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML)
                .param("name", "Drohobych"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/city/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        verify(cityService, times(1)).create(any(City.class));
    }


    @Test
    public void testFindAll() throws Exception {

        City c1 = new City();
        c1.setName("Chernihiv");
        City c2 = new City();
        c2.setName("Drohobych");

        List<City> cityList = List.of(c1, c2);

        when(cityService.findAll()).thenReturn(cityList);

        mockMvc.perform(MockMvcRequestBuilders.get("/city"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(cityService, Mockito.times(1)).findAll();
    }


    @Test
    public void testFindById() throws Exception {

        City c1 = new City();
        c1.setId(1);
        c1.setName("Chernihiv");

        when(cityService.findById(c1.getId())).thenReturn(c1);

        mockMvc.perform(MockMvcRequestBuilders.get("/city/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(cityService, Mockito.times(1)).findById(c1.getId());
    }


    @Test
    public void testDelete() throws Exception {

        City city = new City();
        city.setId(2);
        city.setName("Drohobych");

        when(cityService.findById(city.getId())).thenReturn(city);
        doNothing().when(cityService).delete(city.getId());

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/city/2"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/city"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        verify(cityService, Mockito.times(1)).delete(city.getId());
    }

   @Test
    public void testUpdate() throws Exception {

        City city = new City();
        city.setId(2);

        when(cityService.findById(city.getId())).thenReturn(city);

        mockMvc.perform(MockMvcRequestBuilders.put("/city/2")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML)
                .param("name", "newName"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/city"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}
