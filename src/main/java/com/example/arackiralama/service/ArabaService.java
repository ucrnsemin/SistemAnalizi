package com.example.arackiralama.service;

import com.example.arackiralama.entity.Araba;
import com.example.arackiralama.repository.ArabaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArabaService {

    private final ArabaRepository arabaRepository;

    public ArabaService(ArabaRepository arabaRepository) {
        this.arabaRepository = arabaRepository;
    }

    public List<Araba> findAll() {
        return arabaRepository.findAll();
    }

    public void save(Araba araba) {
        arabaRepository.save(araba);
    }

    // --- YENİ EKLENEN METOTLAR ---

    /**
     * Bir arabayı ID'si ile bulur. Bulamazsa hata fırlatır.
     * Bu metot, düzenleme sayfasını açarken kullanılır.
     * @param id Bulunacak arabanın ID'si
     * @return Bulunan Araba nesnesi
     */
    public Araba findById(Long id) {
        return arabaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Araba ID'si bulunamadı: " + id));
    }

    /**
     * Bir arabayı ID'si ile veritabanından siler.
     * @param id Silinecek arabanın ID'si
     */
    public void deleteById(Long id) {
        arabaRepository.deleteById(id);
    }
}