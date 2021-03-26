package com.example.hello.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class Shape extends DynamoDBIdModel {
    @DynamoDBAttribute
    protected int x;
    @DynamoDBAttribute
    protected int y;
    @DynamoDBAttribute
    protected String color;

    public void setPosition(final int inX, final int inY) {
        x = inX;
        y = inY;
    }
}
