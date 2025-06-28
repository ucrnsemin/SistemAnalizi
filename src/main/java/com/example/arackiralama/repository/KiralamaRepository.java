package com.example.arackiralama.repository;

import com.example.arackiralama.entity.Kiralama;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KiralamaRepository extends JpaRepository<Kiralama, Long> {
}