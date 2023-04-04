package com.Intership.FamilyBudget.dto;

import com.Intership.FamilyBudget.model.ShoppingHistory;
import com.Intership.FamilyBudget.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class ShoppingResponseDTO {
    private int id;

    private String productName;

    private int price;

    private LocalDate buyDate;

    private User user;

    public ShoppingResponseDTO(ShoppingHistory shoppingHistory){
        id = shoppingHistory.getId();
        productName = shoppingHistory.getProductName();
        price = shoppingHistory.getPrice();
        buyDate = shoppingHistory.getBuyDate();
        user = shoppingHistory.getUser();
    }
}
