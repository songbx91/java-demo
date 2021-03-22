package com.example.hello.mapper;

import com.example.hello.mapper.basic.MyMapper;
import com.example.hello.model.Country;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CountryMapper extends MyMapper<Country> {
}
