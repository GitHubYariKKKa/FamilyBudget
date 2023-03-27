package com.Intership.FamilyBudget.service;

import com.Intership.FamilyBudget.model.Family;
import com.Intership.FamilyBudget.model.User;

import java.util.List;

public interface FamilyService {
    Family create(Family family);
    Family update(Family family);
    Family readById(int id);
    Family readByName(String name);
    void delete(int id);
    Family addUserToThisFamily(int family_id, User user);
    Family removeUserFromThisFamily(int family_id, int user_id);
}
