package com.Intership.FamilyBudget.repository;

import com.Intership.FamilyBudget.dto.SortedUsersBySpendingDTO;
import com.Intership.FamilyBudget.model.Family;
import com.Intership.FamilyBudget.model.ShoppingHistory;
import com.Intership.FamilyBudget.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    @Query("select new com.Intership.FamilyBudget.dto.SortedUsersBySpendingDTO(u.name,sum(sh.price)) from User u " +
            "inner join u.shoppingHistory sh " +
            "where sh.buyDate between :startDate and :endDate " +
            "group by u.name, sh.buyDate")
    List<SortedUsersBySpendingDTO> findUserBySpending(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    /*

        @Query("select sum(sh.price) from ShoppingHistory sh" +
            " join sh.users u" +
            " where u.id = :user_id")



    SELECT u.name, DATE(purchase_date), SUM(price) AS total_price
FROM users u
INNER JOIN purchases p ON u.id = p.user_id
WHERE purchase_date BETWEEN :start_date AND :end_date
GROUP BY u.id, DATE(purchase_date)
     */
}
