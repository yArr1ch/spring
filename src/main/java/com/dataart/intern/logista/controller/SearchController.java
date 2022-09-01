package com.dataart.intern.logista.controller;

import com.dataart.intern.logista.model.*;
import com.dataart.intern.logista.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/parcels")
    @PreAuthorize("permitAll()")
    public List<Parcel> findParcelsBy(@RequestParam Integer id) {
        return searchService.findParcelsByCity(id);
    }

    @GetMapping("/clients")
    @PreAuthorize("permitAll()")
    public List<Client> findClientsBy(@RequestParam Integer id) {
        return searchService.findClientsByCity(id);
    }

    @GetMapping("/client")
    @PreAuthorize("permitAll()")
    public List<Client> findClientBy(@RequestParam String number) {
        return searchService.findClientByPhoneNumberLike(number);
    }

    @GetMapping("/{name}")
    @PreAuthorize("permitAll()")
    public List<City> findCitiesBy(@PathVariable String name) {
        return searchService.findCitiesByName(name);
    }

    @GetMapping("/depot")
    @PreAuthorize("permitAll()")
    public List<Depot> findDepotBy(@RequestParam String adr, @RequestParam Integer query) {
        return searchService.findDepotByAddressAndNumberOrCityId(adr, query);
    }

    @GetMapping("/v2/clients/{id}")
    @PreAuthorize("permitAll()")
    public List<ClientDTO> findClientsStream(@PathVariable Integer id) {
        return searchService.findClientsStream(id);
    }

    @GetMapping("/v2/depots")
    @PreAuthorize("permitAll()")
    List<DepotDTO> findDepotsStream(@RequestParam Integer num, @RequestParam String adr) {
        return searchService.findDepotsStream(num, adr);
    }
}
