package com.Intership.FamilyBudget.repository;

import com.Intership.FamilyBudget.model.ShoppingHistory;
import com.Intership.FamilyBudget.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingHistoryRepository extends JpaRepository<ShoppingHistory, Integer> {
    @Query("select sh from ShoppingHistory sh " +
            "join sh.users u " +
            "where :user member of sh.users")
    Optional<ShoppingHistory> findShoppingHistoriesByUser(@Param("user")User user);

    @Query("select sh from ShoppingHistory sh " +
            "join sh.users u " +
            "join u.family fm " +
            "where sh.id = :id ")
    ShoppingHistory findShoppingHistoryByIdInThisFamily(@Param("id") int id);

    @Query("select sh from ShoppingHistory sh " +
            "join sh.users u " +
            "join u.family fm " +
            "where sh.productName = :name ")
    ShoppingHistory findShoppingHistoryByNameInThisFamily(@Param("name") String name);

    @Query("select sh from ShoppingHistory sh " +
            "join sh.users u " +
            "join u.family fm " +
            "where fm.id = :family_id ")
    List<ShoppingHistory> findAllShoppingInFamily(@Param("family_id") int family_id);
}
