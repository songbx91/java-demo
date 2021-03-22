package com.example.hello.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(
    name = "friendships",
    indexes = {
        @Index(
            name = "idx_token",
            columnList = "token",
            unique = true
        ),
        @Index(
            name = "idx_uid1",
            columnList = "uid1"
        ),
        @Index(
            name = "idx_uid2",
            columnList = "uid2"
        ),
        @Index(
            name = "idx_uids",
            columnList = "uid1,uid2"
        )
    }
)
public class Friendship extends BaseModel implements Serializable {
    @Column
    private Integer uid1;
    @Column
    private Integer uid2;
    @Column (length = 4)
    private Integer origin;
    @Column (length = 64)
    private String token;
}
