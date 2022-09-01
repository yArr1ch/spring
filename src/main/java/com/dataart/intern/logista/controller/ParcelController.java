package com.dataart.intern.logista.controller;

import com.dataart.intern.logista.security.NotFoundException;
import com.dataart.intern.logista.model.Parcel;
import com.dataart.intern.logista.service.ParcelService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/parcel")
public class ParcelController {

    private final ParcelService parcelService;

    @GetMapping
    @PreAuthorize("hasRole('OPERATOR')")
    public List<Parcel> find() {
        return parcelService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OPERATOR')")
    public Parcel findById(@PathVariable Integer id) {
        Parcel parcel = parcelService.findById(id).
                orElseThrow(() -> new NotFoundException("Not found parcel with id = " + id));
        return parcel;
    }

    @PostMapping
    @PreAuthorize("hasRole('OPERATOR')")
    public Parcel create(@Valid @RequestBody Parcel parcel) {
        return parcelService.create(parcel);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OPERATOR')")
    public void delete(@PathVariable Integer id) {
        parcelService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OPERATOR')")
    public Parcel update(@PathVariable Integer id, @Valid @RequestBody Parcel parcel) {
        parcel.setId(id);
        return parcelService.update(parcel);
    }
}
