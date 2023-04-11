package com.Intership.FamilyBudget.dto;

import com.Intership.FamilyBudget.model.ShoppingHistory;
import com.Intership.FamilyBudget.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ShoppingRequestDTO {
    private String productName;

    private int price;

    private LocalDate buyDate;

    public ShoppingRequestDTO(ShoppingHistory shoppingHistory){
        productName = shoppingHistory.getProductName();
        price = shoppingHistory.getPrice();
        buyDate = shoppingHistory.getBuyDate();
    }
}
