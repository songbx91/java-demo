package com.example.hello.controller;

import com.example.hello.model.Country;
import com.example.hello.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class CountryController {
    @Autowired
    ICountryService countryService;

    @PostMapping("/country")
    public String create(@RequestParam("code") String code,
                         @RequestParam("name") String name) {
        Country country = new Country();
        country.setName(name);
        country.setCode(code);
        countryService.create(country);
        return "success";
    }

    @GetMapping("/countries")
    public List<Country> getAll(@Nullable @RequestParam String code,
                                @Nullable @RequestParam String name,
                                @Nullable @RequestParam Integer page,
                                @Nullable @RequestParam Integer rows) {
        Country country = new Country();
        country.setCode(code);
        country.setName(name);
        if (page != null && page > 0) {
            country.setPage(page);
        }
        if (rows != null && rows > 0) {
            country.setRows(rows);
        }
        return countryService.getAll(country);
    }
}
