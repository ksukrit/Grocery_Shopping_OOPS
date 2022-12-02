package com.oopsproject.GroceryBasket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseObject {
    int statusCode;
    String type;
    String message;
}
