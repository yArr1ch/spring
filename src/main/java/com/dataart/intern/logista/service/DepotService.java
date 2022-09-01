package com.dataart.intern.logista.service;

import com.dataart.intern.logista.model.Depot;
import com.dataart.intern.logista.repository.DepotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepotService {

    private final DepotRepository depotRepository;

    public List<Depot> findAll() {
        return depotRepository.findAll();
    }

    public Optional<Depot> findById(Integer id) {
        return depotRepository.findById(id);
    }

    public Depot create(Depot depot) {
        return depotRepository.save(depot);
    }

    public void delete(Integer id) {
        depotRepository.deleteById(id);
    }

    public Depot update(Depot depot) {
        return depotRepository.save(depot);
    }
}

