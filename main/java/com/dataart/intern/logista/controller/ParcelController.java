package com.dataart.intern.logista.controller;

import com.dataart.intern.logista.model.Parcel;
import com.dataart.intern.logista.service.ClientService;
import com.dataart.intern.logista.service.DepotService;
import com.dataart.intern.logista.service.ParcelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/parcel")
@AllArgsConstructor
public class ParcelController {

    private final ParcelService parcelService;
    private final ClientService clientService;
    private final DepotService depotService;


    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("parcels", parcelService.findAll());
        model.addAttribute("clients", clientService.findAll());
        return "parcel/list";
    }

    @GetMapping("/{id}")
    public String find(@PathVariable Integer id, Model model) {
        model.addAttribute("parcel", parcelService.findDTO(id));
        return "parcel/view";
    }

    @GetMapping("/create")
    public String getPackageForm(@ModelAttribute Parcel parcel, Model model) {
        model.addAttribute("parcel", parcel);
        model.addAttribute("depots", depotService.findAll());
        model.addAttribute("clients", clientService.findAll());
        return "parcel/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Valid Parcel parcel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("parcel", parcel);
            return "parcel/create";
        }
        int id = parcelService.create(parcel);
        return "redirect:/parcel/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        parcelService.delete(id);
        return "redirect:/parcel";
    }

    @GetMapping("/{id}/update")
    public String getUpdateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("parcel", parcelService.find(id));
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("depots", depotService.findAll());
        return "parcel/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute @Valid Parcel parcel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("parcel", parcel);
            model.addAttribute("clients", clientService.findAll());
            model.addAttribute("depots", depotService.findAll());
            return "parcel/update";
        }
        parcelService.update(id, parcel);
        return "redirect:/parcel";
    }
}