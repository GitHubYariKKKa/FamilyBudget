package com.Intership.FamilyBudget.service;

import com.Intership.FamilyBudget.dto.SortedUsersBySpendingDTO;
import com.Intership.FamilyBudget.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User create(User user);
    User update(User user);
    User readById(int id);
    User readByName(String name);
    User readByLastName(String lastName);
    User readByThirdName(String thirdName);
    void delete(int id);
    List<User> getAll(int family_id);
    List<User> getAllOrderedByBudget(int family_id);
    List<SortedUsersBySpendingDTO> getSortedUsersBySpending(LocalDate startDate,LocalDate endDate);
}
