package com.example.hello.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column (length = 32)
    private String name;
    @Column (length = 16)
    private String tel;
    @Column (length = 128)
    private String email;
    @Column (columnDefinition = "TEXT")
    private String description;
    @Column (length = 32)
    private String token;

    @Override
    public String toString() {
        return "User{ " +
                ", id=" + id +
                ", name=" + name +
                ", tel=" + tel +
                ", email=" + email +
                ", description=" + description + " }";
    }
}
