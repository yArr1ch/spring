package com.dataart.intern.logista.controller;

import com.dataart.intern.logista.model.Depot;
import com.dataart.intern.logista.service.CityService;
import com.dataart.intern.logista.service.DepotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/depot")
public class DepotController {

    private final DepotService depotService;
    private final CityService cityService;

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("depots", depotService.findAll());
        return "depot/list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        model.addAttribute("depot", depotService.findDTO(id));
        return "depot/view";
    }

    @GetMapping("/create")
    public String getDepotForm(@ModelAttribute Depot depot, Model model) {
        model.addAttribute("depot", depot);
        model.addAttribute("cities", cityService.findAll());
        return "depot/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Valid Depot depot, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("depot", depot);
            return "depot/create";
        }
        int id = depotService.create(depot);
        return "redirect:/depot/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        depotService.delete(id);
        return "redirect:/depot";
    }

    @GetMapping("/{id}/update")
    public String getUpdateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("depot", depotService.find(id));
        model.addAttribute("cities", cityService.findAll());
        return "depot/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute @Valid Depot depot, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("depot", depot);
            model.addAttribute("cities", cityService.findAll());
            return "depot/update";
        }
        depotService.update(id, depot);
        return "redirect:/depot";
    }
}
