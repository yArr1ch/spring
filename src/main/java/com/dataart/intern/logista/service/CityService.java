package com.dataart.intern.logista.service;

import com.dataart.intern.logista.model.City;
import com.dataart.intern.logista.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public Optional<City> findById(Integer id) {
        return cityRepository.findById(id);
    }

    public City create(City city) {
        return cityRepository.save(city);
    }

    public void delete(Integer id) {
         cityRepository.deleteById(id);
    }

    public City update(City city) {
         return cityRepository.save(city);
    }
}
