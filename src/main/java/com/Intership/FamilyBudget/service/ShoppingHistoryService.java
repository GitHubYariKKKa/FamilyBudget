package com.Intership.FamilyBudget.service;

import com.Intership.FamilyBudget.dto.SpendPerDayDTO;
import com.Intership.FamilyBudget.model.ShoppingHistory;

import java.time.LocalDate;
import java.util.List;

public interface ShoppingHistoryService {
    ShoppingHistory create(ShoppingHistory shoppingHistory);
    ShoppingHistory update(ShoppingHistory shoppingHistory);
    ShoppingHistory readByIdInThisFamily(int id);
    List<ShoppingHistory> readByBuyDate(LocalDate date);
    ShoppingHistory readByProductNameInThisFamily(String name);
    void delete(int id);
    List<ShoppingHistory> getAll(int family_id);
    List<ShoppingHistory> getAllMyShopping(int user_id);
    List<SpendPerDayDTO> getUsersSpendingPerDayStatistic(int user_id);
}
