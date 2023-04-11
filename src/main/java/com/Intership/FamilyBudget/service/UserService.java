package com.Intership.FamilyBudget.service;

import com.Intership.FamilyBudget.dto.SortedUsersBySpendingDTO;
import com.Intership.FamilyBudget.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User create(User user);
    User update(User user);
    User readById(int id);
    List<User> readByName(String name);
    List<User> readByLastName(String lastName);
    List<User> readByThirdName(String thirdName);
    List<User> readByNameAndSurName(String name, String surName);
    List<User> readByNameAndLastName(String name, String lastName);
    List<User> readBySurNameAndLastName(String surName, String lastName);
    List<User> readByNameAndSurNameAndLastName(String name, String surName, String lastName);
    void delete(int id);
    List<User> getAll();
    List<User> getAllByFamilyId(int family_id);
    List<User> getAllOrderedByBudget(int family_id);
    List<SortedUsersBySpendingDTO> getSortedUsersBySpending(LocalDate startDate,LocalDate endDate, int family_id);
    void removeUserFromThisFamily(int user_id);
}
