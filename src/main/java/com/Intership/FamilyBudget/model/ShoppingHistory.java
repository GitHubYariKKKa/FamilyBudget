package com.Intership.FamilyBudget.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "shopping_history")
@Getter
@Setter
@NoArgsConstructor
public class ShoppingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;

    private int price;

    private LocalDate buyDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
