package com.example.hello.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ArticleId implements Serializable {
    private static final long serialVersionUID = 1L;
    @DynamoDBHashKey(attributeName = "author")
    private String author;
    @DynamoDBRangeKey(attributeName = "sk")
    private String sk;

}
