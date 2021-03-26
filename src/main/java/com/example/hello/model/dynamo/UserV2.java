package com.example.hello.model.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Setter;

@Setter
@DynamoDBTable(tableName = "User")
public class UserV2 {
    private String id;
    private String firstName;
    private String lastName;

    public UserV2() {
        // Default constructor is required by AWS DynamoDB SDK
    }

    public UserV2(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    @DynamoDBAttribute
    public String getFirstName() {
        return firstName;
    }

    @DynamoDBAttribute
    public String getLastName() {
        return lastName;
    }
}
