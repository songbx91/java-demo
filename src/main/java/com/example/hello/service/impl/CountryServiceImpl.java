package com.example.hello.service.impl;

import com.example.hello.mapper.CountryMapper;
import com.example.hello.model.Country;
import com.example.hello.service.ICountryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service
public class CountryServiceImpl implements ICountryService {
    @Autowired
    CountryMapper countryMapper;

    public List<Country> getAll(Country country) {
        if (country.getPage() != null && country.getRows() != null) {
            PageHelper.startPage(country.getPage(), country.getRows());
        }
        Example example = new Example(Country.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(country.getName())) {
            criteria.andLike("name", country.getName() + "%");
        }
        if (StringUtil.isNotEmpty(country.getCode())) {
            criteria.andLike("code", country.getCode() + "%");
        }
        return countryMapper.selectByExample(example);
    }

    public void create(Country country) {
        countryMapper.insert(country);
    }
}
