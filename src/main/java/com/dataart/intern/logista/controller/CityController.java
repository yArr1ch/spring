package com.dataart.intern.logista.controller;
import com.dataart.intern.logista.security.NotFoundException;
import com.dataart.intern.logista.model.City;
import com.dataart.intern.logista.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    public List<City> find() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    public City findById(@PathVariable Integer id) {
        City city = cityService.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found city with id = " + id));
        return city;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public City create(@Valid @RequestBody City city) {
        return cityService.create(city);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Integer id) {
        cityService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public City update(@PathVariable Integer id, @Valid @RequestBody City city) {
        city.setId(id);
        return cityService.update(city);
    }
}
