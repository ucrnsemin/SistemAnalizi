package com.example.arackiralama.repository;

import com.example.arackiralama.entity.Araba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArabaRepository extends JpaRepository<Araba, Long> {

}