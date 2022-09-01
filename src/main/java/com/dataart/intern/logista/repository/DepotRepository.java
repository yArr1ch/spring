package com.dataart.intern.logista.repository;

import com.dataart.intern.logista.model.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepotRepository extends JpaRepository<Depot, Integer> {

    List<Depot> findByAddressAndNumberOrCityId(String address, Integer number, Integer city);
}
