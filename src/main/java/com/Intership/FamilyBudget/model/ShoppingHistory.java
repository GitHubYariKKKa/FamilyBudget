package com.Intership.FamilyBudget.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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

    private boolean isDone;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
