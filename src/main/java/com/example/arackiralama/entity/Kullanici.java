package com.example.arackiralama.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kullanicilar", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;

    private boolean enabled = true;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "kullanici_rolleri",
            joinColumns = @JoinColumn(name = "kullanici_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roller = new HashSet<>();

    // --- Constructors ---
    public Kullanici() {
    }

    public Kullanici(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Rol> getRoller() {
        return roller;
    }

    public void setRoller(Set<Rol> roller) {
        this.roller = roller;
    }

    // Rol eklemek için yardımcı bir metod
    public void rolEkle(Rol rol) {
        this.roller.add(rol);
    }
}