package com.example.arackiralama.repository;

import com.example.arackiralama.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    // İsmine göre Rol bulmamızı sağlayacak özel bir metod
    Rol findByName(String name);
}