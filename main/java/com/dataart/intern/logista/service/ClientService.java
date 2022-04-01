package com.dataart.intern.logista.service;

import com.dataart.intern.logista.model.Client;
import com.dataart.intern.logista.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Integer id) {
        return clientRepository.findById(id);
    }

    public int create(Client client) {
        return clientRepository.create(client);
    }

    public void delete(Integer id) {
        clientRepository.delete(id);
    }

    public void update(Integer id, Client client) {
        clientRepository.update(id, client);
    }
}
