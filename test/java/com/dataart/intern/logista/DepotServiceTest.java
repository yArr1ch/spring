package com.dataart.intern.logista;

import com.dataart.intern.logista.model.City;
import com.dataart.intern.logista.model.Depot;
import com.dataart.intern.logista.model.DepotDTO;
import com.dataart.intern.logista.repository.DepotRepository;
import com.dataart.intern.logista.service.CityService;
import com.dataart.intern.logista.service.DepotService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@WebMvcTest(DepotService.class)
public class DepotServiceTest {

    @MockBean
    private CityService cityService;

    @Autowired
    private DepotService depotService;

    @MockBean
    private DepotRepository depotRepository;

    @Test
    public void testFindAll() {

        Depot d1 = new Depot();
        Depot d2 = new Depot();

        d1.setId(1);
        d1.setNumber(234);
        d1.setAddress("newAddress");
        d1.setCityId(3);

        d2.setId(2);
        d2.setNumber(456);
        d2.setAddress("oldAddress");
        d2.setCityId(5);
        List<Depot> depotList = List.of(d1, d2);

        when(depotRepository.findAll()).thenReturn(depotList);

        City c1 = new City();
        City c2 = new City();

        c1.setId(3);
        c1.setName("newCity");

        c2.setId(5);
        c2.setName("oldCity");
        List<City> cityList = List.of(c1, c2);

        when(cityService.findAll()).thenReturn(cityList);

        List<DepotDTO> dto = depotService.findAll();

        Map<Integer, String> citiesMap = cityList.stream().collect(Collectors.toMap(City::getId, City::getName));

            DepotDTO depotDTO = new DepotDTO();
            depotDTO.setId(d1.getId());
            depotDTO.setNumber(d1.getNumber());
            depotDTO.setAddress(d1.getAddress());
            String cityName = citiesMap.get(d1.getCityId());
            depotDTO.setCity(cityName);

            DepotDTO depotDTO1 = new DepotDTO();
            depotDTO1.setId(d2.getId());
            depotDTO1.setNumber(d2.getNumber());
            depotDTO1.setAddress(d2.getAddress());
            String cityName1 = citiesMap.get(d2.getCityId());
            depotDTO1.setCity(cityName1);

            List<DepotDTO> dtoList = List.of(depotDTO, depotDTO1);


        Assertions.assertEquals(dto.get(0).getNumber(), dtoList.get(0).getNumber());
        Assertions.assertEquals(dto.get(1).getAddress(), dtoList.get(1).getAddress());
        Assertions.assertEquals(dto.get(1).getCity(), dtoList.get(1).getCity());
        }
}
