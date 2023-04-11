package com.Intership.FamilyBudget.repository;

import com.Intership.FamilyBudget.dto.SpendPerDayDTO;
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
public interface ShoppingHistoryRepository extends JpaRepository<ShoppingHistory, Integer> {
    @Query("select sh from ShoppingHistory sh " +
            "join sh.user u " +
            "where u.id = :user_id")
    List<ShoppingHistory> findShoppingHistoriesByUser(@Param("user_id")int user_id);

    @Query("select sh from ShoppingHistory sh " +
            "join sh.user u " +
            "join u.family fm " +
            "where sh.id = :id ")
    ShoppingHistory findShoppingHistoryByIdInThisFamily(@Param("id") int id);

    @Query("select sh from ShoppingHistory sh " +
            "join sh.user u " +
            "join u.family fm " +
            "where sh.productName = :name ")
    ShoppingHistory findShoppingHistoryByNameInThisFamily(@Param("name") String name);

    @Query("select sh from ShoppingHistory sh " +
            "join sh.user u " +
            "join u.family fm " +
            "where fm.id = :family_id ")
    List<ShoppingHistory> findAllShoppingInFamily(@Param("family_id") int family_id);

    @Query("select new com.Intership.FamilyBudget.dto.SpendPerDayDTO(sh.buyDate,sum(sh.price)) from ShoppingHistory sh " +
            "join User u on sh.user.id = u.id " +
            "where u.id = :user_id and sh.isDone = false " +
            "and sh.buyDate between :startDate and :endDate" +
            " group by sh.buyDate")
    List<SpendPerDayDTO> findUserSendingPerDay(@Param("user_id") int user_id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
