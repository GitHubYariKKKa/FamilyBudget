package com.Intership.FamilyBudget.service.impl;

import com.Intership.FamilyBudget.model.User;
import com.Intership.FamilyBudget.repository.UserRepository;
import com.Intership.FamilyBudget.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public User readByName(String name) {
        return userRepository.findUserByName(name).orElseThrow(
                NullPointerException::new
        );
    }

    @Override
    public User readByLastName(String surName) {
        return userRepository.findUserBySurName(surName).orElseThrow(
                NullPointerException::new
        );
    }

    @Override
    public User readByThirdName(String lastName) {
        return userRepository.findUserByLastName(lastName).orElseThrow(
                NullPointerException::new
        );
    }

    @Override
    public void delete(int id) {
        userRepository.delete(readById(id));
    }

    @Override
    public List<User> getAll(int family_id) {
        List<User> users = userRepository.findAllByFamilyId(family_id);
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    public List<User> getAllOrderedByBudget(int family_id) {
        List<User> users = userRepository.findUserByFamilyIdOrderByBudget(family_id);
        return users.isEmpty() ? new ArrayList<>() : users;
    }
}
