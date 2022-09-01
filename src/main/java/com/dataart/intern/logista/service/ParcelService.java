package com.dataart.intern.logista.service;

import com.dataart.intern.logista.model.Parcel;
import com.dataart.intern.logista.repository.ParcelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ParcelService {

    private final ParcelRepository parcelRepository;

    public List<Parcel> findAll() {
        return parcelRepository.findAll();
    }

    public Optional<Parcel> findById(Integer id) {
        return parcelRepository.findById(id);
    }

    public Parcel create(Parcel parcel) {
        return parcelRepository.save(parcel);
    }

    public void delete(Integer id) {
        parcelRepository.deleteById(id);
    }

    public Parcel update(Parcel parcel) {
        return parcelRepository.save(parcel);
    }
}
