package com.Intership.FamilyBudget.service;

import com.Intership.FamilyBudget.model.role.Role;

public interface RoleService {
    Role readById(int id);
    Role getByName(String s);
}
