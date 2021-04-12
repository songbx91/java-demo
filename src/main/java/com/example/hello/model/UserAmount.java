package com.example.hello.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
public class UserAmount extends BaseModel implements Serializable {
    @Column
    private Integer money;

    @Column
    private Integer moneySubscribe;

    @Column
    private Integer moneyFree;
}
