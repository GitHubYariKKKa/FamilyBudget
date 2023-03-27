package com.Intership.FamilyBudget.service.impl;

import com.Intership.FamilyBudget.model.Family;
import com.Intership.FamilyBudget.model.User;
import com.Intership.FamilyBudget.repository.FamilyRepository;
import com.Intership.FamilyBudget.service.FamilyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;

    public FamilyServiceImpl(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    @Override
    public Family create(Family family) {
        if (family!=null){
            familyRepository.save(family);
        }
        return family;
    }

    @Override
    public Family update(Family family) {
        if (family!=null){
            readById(family.getId());
            familyRepository.save(family);
        }
        return family;
    }

    @Override
    public Family readById(int id) {
        return familyRepository.findById(id).orElseThrow(
                NullPointerException::new
        );
    }

    @Override
    public Family readByName(String name) {
        return familyRepository.findFamilyByName(name);
    }

    @Override
    public void delete(int id) {
        familyRepository.delete(readById(id));
    }

    @Override
    public Family addUserToThisFamily(int family_id, User user) {
        Family family = readById(family_id);
        family.getUsers().add(user);
        return family;
    }

    @Override
    public Family removeUserFromThisFamily(int family_id, int user_id) {
        Family family = readById(family_id);
        for(User user: family.getUsers()){
            if (user.getId() == user_id){
                family.getUsers().remove(user);
            }
        }
        return family;
    }
}
