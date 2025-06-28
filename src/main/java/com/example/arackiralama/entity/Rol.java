package com.example.arackiralama.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roller")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 45)
    private String name;

    // --- Constructors, Getters and Setters ---
    public Rol() {
    }

    public Rol(String name) {
        this.name = name;
    }

    // IntelliJ (Alt+Insert) ile getter ve setter'ları ekleyebilirsin.

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}