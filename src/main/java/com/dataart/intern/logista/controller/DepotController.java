package com.dataart.intern.logista.controller;

import com.dataart.intern.logista.security.NotFoundException;
import com.dataart.intern.logista.model.Depot;
import com.dataart.intern.logista.service.DepotService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/depot")
public class DepotController {

    private final DepotService depotService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<Depot> find() {
        return depotService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public Depot findById(@PathVariable Integer id) {
        Depot depot = depotService.findById(id).
                orElseThrow(() -> new NotFoundException("Not found depot with id = " + id));
        return depot;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Depot create(@Valid @RequestBody Depot depot) {
        return depotService.create(depot);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Integer id) {
        depotService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Depot update(@PathVariable Integer id, @Valid @RequestBody Depot depot) {
        depot.setId(id);
        return depotService.update(depot);
    }
}

