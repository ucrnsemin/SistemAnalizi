package com.example.arackiralama;

import com.example.arackiralama.entity.Araba;
import com.example.arackiralama.entity.Kullanici;
import com.example.arackiralama.entity.Rol;
import com.example.arackiralama.repository.ArabaRepository;
import com.example.arackiralama.repository.KullaniciRepository;
import com.example.arackiralama.repository.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional; // BU SATIRI EKLE

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final KullaniciRepository kullaniciRepository;
    private final ArabaRepository arabaRepository;

    public DataInitializer(RolRepository rolRepository, KullaniciRepository kullaniciRepository, ArabaRepository arabaRepository) {
        this.rolRepository = rolRepository;
        this.kullaniciRepository = kullaniciRepository;
        this.arabaRepository = arabaRepository;
    }

    @Override
    @Transactional // BÜTÜN SORUNU ÇÖZECEK OLAN SİHİRLİ SATIR BU
    public void run(String... args) throws Exception {
        // Roller sadece veritabanında yoksa oluşturulsun
        if (rolRepository.count() == 0) {
            rolRepository.save(new Rol("ADMIN"));
            rolRepository.save(new Rol("USER"));
        }

        // Kullanıcılar sadece veritabanında yoksa oluşturulsun
        if (kullaniciRepository.count() == 0) {
            Rol adminRol = rolRepository.findByName("ADMIN");
            Rol userRol = rolRepository.findByName("USER");

            // Admin Kullanıcısı
            Kullanici admin = new Kullanici();
            admin.setUsername("admin");
            admin.setPassword("12345");
            admin.setRoller(new HashSet<>(Set.of(adminRol)));
            kullaniciRepository.save(admin);

            // Normal Kullanıcı
            Kullanici user = new Kullanici();
            user.setUsername("user");
            user.setPassword("123");
            user.setRoller(new HashSet<>(Set.of(userRol)));
            kullaniciRepository.save(user);
        }

        // Arabalar sadece veritabanında yoksa oluşturulsun
        if (arabaRepository.count() == 0) {
            arabaRepository.save(new Araba("Toyota", "Corolla", 2023, 1500.0));
            arabaRepository.save(new Araba("Renault", "Clio", 2022, 1200.0));
            arabaRepository.save(new Araba("Ford", "Focus", 2024, 1700.0));
            arabaRepository.save(new Araba("BMW", "320i", 2023, 3500.0));
        }
    }
}