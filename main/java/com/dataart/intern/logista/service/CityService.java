package com.dataart.intern.logista.service;

import com.dataart.intern.logista.model.City;
import com.dataart.intern.logista.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findById(Integer id) {
        return cityRepository.findById(id);
    }

    public int create(City city) {
        return cityRepository.create(city);
    }

    public void delete(Integer id) {
        cityRepository.delete(id);
    }

    public void update(Integer id, City city) {
        cityRepository.update(id, city);
    }
}
