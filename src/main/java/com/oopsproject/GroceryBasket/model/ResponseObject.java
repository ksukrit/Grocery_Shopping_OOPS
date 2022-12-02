package com.oopsproject.GroceryBasket.model;

import lombok.Data;

@Data
public class ResponseObject {
    int statusCode;
    String type;
    String message;
}
