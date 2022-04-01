package com.dataart.intern.logista.controller;

import com.dataart.intern.logista.model.City;
import com.dataart.intern.logista.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("cities", cityService.findAll());
        return "city/list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        City city = cityService.findById(id);
        model.addAttribute("city", city);
        return "city/view";
    }

    @GetMapping("/create")
    public String getCityForm(@ModelAttribute City city, Model model) {
        model.addAttribute("city", city);
        return "city/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Valid City city, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("city", city);
            return "city/create";
        }
        int id = cityService.create(city);
        return "redirect:/city/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        cityService.delete(id);
        return "redirect:/city";
    }

    @GetMapping("/{id}/update")
    public String getUpdateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("city", cityService.findById(id));
        return "city/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute @Valid City city, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("city", city);
            return "city/update";
        }
        cityService.update(id, city);
        return "redirect:/city";
    }
}
