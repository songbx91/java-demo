package com.example.hello.service;

import com.example.hello.model.Country;

import java.util.List;

public interface ICountryService {
    List<Country> getAll(Country country);
    void create(Country country);
}
