package com.dataart.intern.logista.controller;

import com.dataart.intern.logista.model.Client;
import com.dataart.intern.logista.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "client/list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        Client client = clientService.findById(id);
        model.addAttribute("client", client);
        return "client/view";
    }

    @GetMapping("/create")
    public String getClientsForm(@ModelAttribute Client client, Model model) {
        model.addAttribute("client", client);
        return "client/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Valid Client client, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("client", client);
            return "client/create";
        }
        int id = clientService.create(client);
        return "redirect:/client/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        clientService.delete(id);
        return "redirect:/client";
    }

    @GetMapping("/{id}/update")
    public String getUpdateForm(Model model, @PathVariable Integer id) {
        model.addAttribute("client", clientService.findById(id));
        return "client/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute @Valid Client client, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("client", client);
            return "client/update";
        }
        clientService.update(id, client);
        return "redirect:/client";
    }
}
