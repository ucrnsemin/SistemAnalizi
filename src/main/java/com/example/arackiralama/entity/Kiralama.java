package com.example.arackiralama.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "kiralamalar")
public class Kiralama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Bir kiralama kaydını çekerken her seferinde arabayı çekme, gerekirse çek.
    @JoinColumn(name = "araba_id", nullable = false)
    private Araba araba;

    @ManyToOne(fetch = FetchType.LAZY) // Kullanıcıyı da sadece gerekirse çek.
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;

    @Column(name = "tc_no")
    private String tcNo;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "adres", length = 500)
    private String adres;

    @Column(name = "baslangic_tarihi")
    private LocalDate baslangicTarihi;

    @Column(name = "bitis_tarihi")
    private LocalDate bitisTarihi;

    @Column(name = "toplam_fiyat")
    private double toplamFiyat;

    // --- Constructors ---
    public Kiralama() {
    }

    // --- Getters and Setters (En Önemli Kısım) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Araba getAraba() {
        return araba;
    }

    public void setAraba(Araba araba) {
        this.araba = araba;
    }

    public Kullanici getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public String getTcNo() {
        return tcNo;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public LocalDate getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(LocalDate baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public LocalDate getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(LocalDate bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public double getToplamFiyat() {
        return toplamFiyat;
    }

    public void setToplamFiyat(double toplamFiyat) {
        this.toplamFiyat = toplamFiyat;
    }
}