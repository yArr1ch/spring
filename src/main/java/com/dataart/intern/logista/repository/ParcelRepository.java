package com.dataart.intern.logista.repository;

import com.dataart.intern.logista.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Integer> {

    @Query("SELECT DISTINCT p FROM Parcel p INNER JOIN Depot d " +
            "ON p.originId = d.id OR p.destinationId = d.id WHERE d.cityId = :id")
    List<Parcel> findParcelsByCity(Integer id);
}
