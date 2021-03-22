package com.example.hello.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(
    name = "blacklist",
    indexes = {
        @Index(
            name = "idx_user_id",
            columnList = "userId"
        )
    }
)
public class Blacklist extends BaseModel implements Serializable {
    @Column
    private Integer userId;
    @Column
    private Integer blockedUserId;
}
