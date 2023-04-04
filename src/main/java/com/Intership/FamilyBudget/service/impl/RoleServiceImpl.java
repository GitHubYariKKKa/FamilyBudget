package com.Intership.FamilyBudget.service.impl;

import com.Intership.FamilyBudget.model.role.Role;
import com.Intership.FamilyBudget.repository.RoleRepository;
import com.Intership.FamilyBudget.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role readById(int id) {
        return roleRepository.findById(id).orElseThrow(
                NullPointerException::new
        );
    }

    @Override
    public Role getByName(String s) {
        return roleRepository.findAll().stream()
                .filter(a -> (a.getRole()).equals(s))
                .findFirst()
                .orElse(null);
    }
}
