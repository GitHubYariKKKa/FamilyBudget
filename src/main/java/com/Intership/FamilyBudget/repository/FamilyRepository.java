package com.Intership.FamilyBudget.repository;

import com.Intership.FamilyBudget.model.Family;
import com.Intership.FamilyBudget.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Integer> {

    @Query("select f from Family f " +
            "join User u on f.id = u.family.id" +
            " where u.id = :user_id")
    Optional<Family> findFamilyByUsers(@Param("user_id") int user_id);

    Family findFamilyByName(String name);

    @Query("select f from Family f " +
            "join f.users u " +
            "join u.shoppingHistory sh " +
            "where sh.id = :id")
    Family getFamilyByShoppingHistoryId(int id);

}
