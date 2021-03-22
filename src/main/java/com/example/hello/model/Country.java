package com.example.hello.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity

public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Transient
    private Integer page = 1;
    @Transient
    private Integer rows = 10;

    private String name;
    private String code;
}
