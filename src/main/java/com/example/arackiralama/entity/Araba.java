package com.example.arackiralama.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "arabalar")
public class Araba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marka;
    private String model;
    private int yil;
    private double gunlukFiyat;
    private boolean musait = true; // Varsayılan olarak her araba müsaittir

    // --- ÇÖZÜM BURADA BAŞLIYOR ---

    // 1. JPA'nın (veritabanından nesne oluştururken) kullanması için BOŞ CONSTRUCTOR zorunludur.
    public Araba() {
    }

    // 2. DataInitializer'da kolayca nesne oluşturmak için KULLANDIĞIMIZ CONSTRUCTOR.
    public Araba(String marka, String model, int yil, double gunlukFiyat) {
        this.marka = marka;
        this.model = model;
        this.yil = yil;
        this.gunlukFiyat = gunlukFiyat;
    }

    // --- ÇÖZÜM BURADA BİTİYOR ---


    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYil() {
        return yil;
    }

    public void setYil(int yil) {
        this.yil = yil;
    }

    public double getGunlukFiyat() {
        return gunlukFiyat;
    }

    public boolean isMusait() {
        return musait;
    }

    public void setMusait(boolean musait) {
        this.musait = musait;
    }

    public void setGunlukFiyat(double gunlukFiyat) {
        this.gunlukFiyat = gunlukFiyat;
    }
}