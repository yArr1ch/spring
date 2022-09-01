package com.dataart.intern.logista.controller;

import com.dataart.intern.logista.security.NotFoundException;
import com.dataart.intern.logista.model.Client;
import com.dataart.intern.logista.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @PreAuthorize("hasRole('OPERATOR')")
    public List<Client> find() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OPERATOR')")
    public Client findById(@PathVariable Integer id) {
        Client client = clientService.findById(id).
                orElseThrow(() -> new NotFoundException("Not found client with id = " + id));
        return client;
    }

    @PostMapping
    @PreAuthorize("hasRole('OPERATOR')")
    public Client create(@Valid @RequestBody Client client) {
        return clientService.create(client);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OPERATOR')")
    public void delete(@PathVariable Integer id) {
        clientService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OPERATOR')")
    public Client update(@PathVariable Integer id, @Valid @RequestBody Client client) {
        client.setId(id);
        return clientService.update(client);
    }
}
