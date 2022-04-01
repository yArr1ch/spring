package com.dataart.intern.logista.service;

import com.dataart.intern.logista.model.City;
import com.dataart.intern.logista.model.DepotDTO;
import com.dataart.intern.logista.model.Depot;
import com.dataart.intern.logista.repository.DepotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepotService {

    private final DepotRepository depotRepository;
    private final CityService cityService;

    public List<DepotDTO> findAll() {
        List<Depot> depot = depotRepository.findAll();
        List<City> city = cityService.findAll();
        Map<Integer, String> citiesMap = city.stream().collect(Collectors.toMap(City::getId, City::getName));
        return depot.stream().map(depots -> {
            DepotDTO depotDTO = new DepotDTO();
            depotDTO.setId(depots.getId());
            depotDTO.setNumber(depots.getNumber());
            depotDTO.setAddress(depots.getAddress());
            String cityName = citiesMap.get(depots.getCityId());
            depotDTO.setCity(cityName);
            return depotDTO;
        }).collect(Collectors.toList());
    }

    public Depot find(Integer id) {
        return depotRepository.findById(id);
    }

    public DepotDTO findDTO(Integer id) {
        Depot depot = depotRepository.findById(id);
        List<City> city = cityService.findAll();
        Map<Integer, String> CityMap = city.stream().collect(Collectors.toMap(City::getId, City::getName));
        DepotDTO depotDTO = new DepotDTO();
        depotDTO.setId(depot.getId());
        depotDTO.setNumber(depot.getNumber());
        depotDTO.setAddress(depot.getAddress());
        String cityName = CityMap.get(depot.getCityId());
        depotDTO.setCity(cityName);
        return depotDTO;
    }

    public int create(Depot depot) {
        return depotRepository.create(depot);
    }

    public void delete(Integer id) {
        depotRepository.delete(id);
    }

    public void update(Integer id, Depot depot) {
        depotRepository.update(id, depot);
    }
}
