package com.example.hello.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "circles")
public class Circle extends Shape {
    public static final int DEFAULT_RADIUS = 10;

    @DynamoDBAttribute
    protected int radius = DEFAULT_RADIUS;
}
