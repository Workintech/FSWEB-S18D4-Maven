package com.workintech.s18d1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "burger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Burger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(name = "is_vegan", nullable = false)
    private Boolean isVegan;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BreadType breadType;

    @Column(nullable = false)
    private String contents;
}
