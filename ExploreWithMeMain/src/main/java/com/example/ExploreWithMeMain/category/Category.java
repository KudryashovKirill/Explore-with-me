package com.example.ExploreWithMeMain.category;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Table(name = "categories")
@Entity
@Builder
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 250)
    private String name;
}
