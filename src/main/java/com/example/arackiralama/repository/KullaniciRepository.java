package com.example.arackiralama.repository;

import com.example.arackiralama.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {

    // Kullanıcı adına göre Kullanıcı bulmamızı sağlayacak metod
    // Spring Security bu metodu kullanarak kullanıcı doğrulama yapacak
    Optional<Kullanici> findByUsername(String username);
}