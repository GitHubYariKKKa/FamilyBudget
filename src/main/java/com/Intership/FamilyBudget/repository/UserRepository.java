package com.Intership.FamilyBudget.repository;

import com.Intership.FamilyBudget.model.Family;
import com.Intership.FamilyBudget.model.ShoppingHistory;
import com.Intership.FamilyBudget.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByName(String name);

    Optional<User> findUserByLastName(String lastName);

    Optional<User> findUserBySurName(String surName);

    @Query("select u from User u " +
            "join u.shoppingHistory sh " +
            "join u.family f " +
            "where :shoppingHistory member of u.shoppingHistory " +
            "and f.id = :family_id")
    Optional<User> getUserByShoppingHistoryAndFamily(@Param("shoppingHistory") ShoppingHistory shoppingHistory, @Param("family_id")int family_id);

    List<User> findUserByFamilyIdOrderByBudget(int family_id);

    List<User> findAllByFamilyId(int family_id);

//    @Query("select u from User u ")
//    Optional<User> findUserBySpending();

}
