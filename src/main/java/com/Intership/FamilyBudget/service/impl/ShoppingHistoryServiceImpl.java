package com.Intership.FamilyBudget.service.impl;

import com.Intership.FamilyBudget.dto.SpendPerDayDTO;
import com.Intership.FamilyBudget.model.ShoppingHistory;
import com.Intership.FamilyBudget.repository.ShoppingHistoryRepository;
import com.Intership.FamilyBudget.service.ShoppingHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingHistoryServiceImpl implements ShoppingHistoryService {

    private final ShoppingHistoryRepository shoppingHistoryRepository;

    public ShoppingHistoryServiceImpl(ShoppingHistoryRepository shoppingHistoryRepository) {
        this.shoppingHistoryRepository = shoppingHistoryRepository;
    }

    @Override
    public ShoppingHistory create(ShoppingHistory shoppingHistory) {
        if(shoppingHistory!=null){
            shoppingHistoryRepository.save(shoppingHistory);
        }
        return shoppingHistory;
    }

    @Override
    public ShoppingHistory update(ShoppingHistory shoppingHistory) {
        if(shoppingHistory!=null){
            readByIdInThisFamily(shoppingHistory.getId());
            shoppingHistoryRepository.save(shoppingHistory);
        }
        return shoppingHistory;
    }

    @Override
    public ShoppingHistory readByIdInThisFamily(int id) {
        return shoppingHistoryRepository.findShoppingHistoryByIdInThisFamily(id);
    }

    @Override
    public List<ShoppingHistory> readByBuyDate(LocalDate date) {
        return null;
    }

    @Override
    public ShoppingHistory readByProductNameInThisFamily(String name) {
        return shoppingHistoryRepository.findShoppingHistoryByNameInThisFamily(name);
    }

    @Override
    public ShoppingHistory readById(int id) {
        return shoppingHistoryRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public void delete(int id) {
        shoppingHistoryRepository.delete(readById(id));
    }

    @Override
    public List<ShoppingHistory> getAll(int family_id) {
        List<ShoppingHistory> shoppingHistories = shoppingHistoryRepository.findAllShoppingInFamily(family_id);
        return shoppingHistories.isEmpty() ? new ArrayList<>() : shoppingHistories;
    }

    @Override
    public List<ShoppingHistory> getAllMyShopping(int user_id) {
        List<ShoppingHistory> shoppingHistories = shoppingHistoryRepository.findShoppingHistoriesByUser(user_id);
        return shoppingHistories.isEmpty() ? new ArrayList<>() : shoppingHistories;
    }

    @Override
    public List<SpendPerDayDTO> getUsersSpendingPerDayStatistic(int user_id) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = LocalDate.now().minusDays(30);
        List<SpendPerDayDTO> spendPerDayDTOs = shoppingHistoryRepository.findUserSendingPerDay(user_id,startDate,endDate);
        return spendPerDayDTOs.isEmpty() ? new ArrayList<>() : spendPerDayDTOs;
    }

    @Override
    public void clearShopping(int user_id) {
        List<ShoppingHistory> shoppingHistories = shoppingHistoryRepository.findShoppingHistoryByUserId(user_id);
        for (ShoppingHistory sh:shoppingHistories){
            delete(sh.getId());
        }
    }
}
