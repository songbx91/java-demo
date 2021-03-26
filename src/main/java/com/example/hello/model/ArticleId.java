package com.example.hello.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@ToString
public class ArticleId implements Serializable {
    private static final long serialVersionUID = 1L;
    @DynamoDBAttribute(attributeName = "author")
    private String author;
    @DynamoDBAttribute(attributeName = "sk")
    private String sk;

    @DynamoDBHashKey
    public String getAuthor() {
        return author;
    }

    @DynamoDBRangeKey
    public String getSk() {
        return sk;
    }
}
