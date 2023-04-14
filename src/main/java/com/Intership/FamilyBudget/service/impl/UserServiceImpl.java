package com.Intership.FamilyBudget.service.impl;

import com.Intership.FamilyBudget.dto.SortedUsersBySpendingDTO;
import com.Intership.FamilyBudget.model.Family;
import com.Intership.FamilyBudget.model.User;
import com.Intership.FamilyBudget.repository.FamilyRepository;
import com.Intership.FamilyBudget.repository.UserRepository;
import com.Intership.FamilyBudget.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;

    public UserServiceImpl(UserRepository userRepository, FamilyRepository familyRepository) {
        this.userRepository = userRepository;
        this.familyRepository = familyRepository;
    }

    @Override
    public User create(User user) {
        if (user!=null){
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public User update(User user) {
        if (user!=null){
            readById(user.getId());
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public User readById(int id) {
        return userRepository.findById(id).orElseThrow(
                NullPointerException::new
        );
    }

    @Override
    public User readByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> readByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public List<User> readByLastName(String surName) {
        return userRepository.findUserBySurName(surName);
    }

    @Override
    public List<User> readByThirdName(String lastName) {
        return userRepository.findUserByLastName(lastName);
    }

    @Override
    public List<User> readByNameAndSurName(String name, String surName) {
        return userRepository.findUsersByNameAndSurName(name, surName);
    }

    @Override
    public List<User> readByNameAndLastName(String name, String lastName) {
        return userRepository.findUsersByNameAndLastName(name, lastName);
    }

    @Override
    public List<User> readBySurNameAndLastName(String surName, String lastName) {
        return userRepository.findUsersBySurNameAndLastName(surName,lastName);
    }

    @Override
    public List<User> readByNameAndSurNameAndLastName(String name, String surName, String lastName) {
        return userRepository.findUsersByNameAndSurNameAndLastName(name, surName, lastName);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(readById(id));
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    public List<User> getAllOutOffFamily() {
        List<User> users = userRepository.findAllOutOffFamily();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    public List<User> getAllInFamily(int family_id) {
        List<User> users = userRepository.findAllInFamily(family_id);
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    public List<User> getAllOrderedByBudget(int family_id) {
        List<User> users = userRepository.findUserByFamilyIdOrderByBudget(family_id);
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    public List<SortedUsersBySpendingDTO> getSortedUsersBySpending(LocalDate startDate, LocalDate endDate, int family_id) {
        List<SortedUsersBySpendingDTO> sortedUsersBySpendingDTOS = userRepository.findUserBySpending(family_id);
        return sortedUsersBySpendingDTOS.isEmpty() ? new ArrayList<>() : sortedUsersBySpendingDTOS;
    }

    @Override
    public void removeUserFromThisFamily(int user_id) {
        User user = readById(user_id);
        user.setFamily(null);
        userRepository.save(user);
    }

    @Override
    public void addUserToThisFamily(int user_id, int family_id) {
        User user = readById(user_id);
        Family family = familyRepository.findById(family_id).orElseThrow(NullPointerException::new);
        user.setFamily(family);
        userRepository.save(user);
    }

}
