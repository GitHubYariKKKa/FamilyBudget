package com.Intership.FamilyBudget.repository;

import com.Intership.FamilyBudget.dto.SortedUsersBySpendingDTO;
import com.Intership.FamilyBudget.dto.SpendPerDayDTO;
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
    List<User> findUserByName(String name);

    List<User> findUserByLastName(String lastName);

    List<User> findUserBySurName(String surName);

    List<User> findUsersByNameAndSurName(String name, String surName);

    User findUserByEmail(String email);

    List<User> findUsersByNameAndLastName(String name, String lastName);

    List<User> findUsersBySurNameAndLastName(String surName, String lastName);

    List<User> findUsersByNameAndSurNameAndLastName(String name, String surName,String lastName);

    @Query("select u from User u " +
            "join u.shoppingHistory sh " +
            "join u.family f " +
            "where :shoppingHistory member of u.shoppingHistory " +
            "and f.id = :family_id")
    Optional<User> getUserByShoppingHistoryAndFamily(@Param("shoppingHistory") ShoppingHistory shoppingHistory, @Param("family_id")int family_id);

    List<User> findUserByFamilyIdOrderByBudget(int family_id);

    @Query("select u from User u join u.family f where f.id=:family_id")
    List<User> findAllByFamilyId(@Param("family_id")int family_id);

    @Query("select new com.Intership.FamilyBudget.dto.SortedUsersBySpendingDTO(u.name,sum(sh.price),sh.buyDate) from ShoppingHistory sh " +
            "join User u on sh.user.id = u.id where u.family.id = :family_id and sh.isDone = false group by u.name, sh.buyDate")
    List<SortedUsersBySpendingDTO> findUserBySpending( @Param("family_id") int family_id);


    /*

    @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,

,@Param("family_id") int family_id
"where u.family.id = :family_id " +
            "and sh.buyDate between :startDate and :endDate group by u.name, sh.buyDate"

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
