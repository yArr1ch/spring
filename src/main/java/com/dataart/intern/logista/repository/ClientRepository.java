package com.dataart.intern.logista.repository;

import com.dataart.intern.logista.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("SELECT DISTINCT c FROM Client c INNER JOIN Parcel p ON c.id = p.senderId OR c.id = p.receiverId " +
            "INNER JOIN Depot d ON p.originId = d.id OR p.destinationId = d.id WHERE d.cityId = :id")
    List<Client> findClientsByCity(Integer id);

    @Query("SELECT c FROM Client c WHERE c.phoneNumber LIKE :number%")
    List<Client> findClientByPhoneNumberLike(String number);
}
