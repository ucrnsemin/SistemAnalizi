package com.example.arackiralama.service;

import com.example.arackiralama.entity.Araba;
import com.example.arackiralama.entity.Kiralama;
import com.example.arackiralama.repository.ArabaRepository;
import com.example.arackiralama.repository.KiralamaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;

@Service
public class KiralamaService {

    private final KiralamaRepository kiralamaRepository;
    private final ArabaRepository arabaRepository;

    public KiralamaService(KiralamaRepository kiralamaRepository, ArabaRepository arabaRepository) {
        this.kiralamaRepository = kiralamaRepository;
        this.arabaRepository = arabaRepository;
    }

    @Transactional // Bu metodun tamamı tek bir veritabanı işlemidir. Hata olursa tüm işlemler geri alınır.
    public Kiralama kirala(Kiralama kiralama) {
        // 1. Kiralanmak istenen arabanın en güncel halini veritabanından al
        Araba araba = arabaRepository.findById(kiralama.getAraba().getId())
                .orElseThrow(() -> new IllegalStateException("Bu ID'ye sahip araba bulunamadı."));

        // 2. Arabanın müsait olup olmadığını kontrol et
        if (!araba.isMusait()) {
            throw new IllegalStateException("Bu araba şu anda kiralanamaz.");
        }

        // 3. Toplam fiyatı hesapla
        long gunSayisi = ChronoUnit.DAYS.between(kiralama.getBaslangicTarihi(), kiralama.getBitisTarihi());
        if(gunSayisi <= 0) gunSayisi = 1; // En az 1 gün
        double toplamFiyat = gunSayisi * araba.getGunlukFiyat();
        kiralama.setToplamFiyat(toplamFiyat);

        // 4. Arabanın durumunu "müsait değil" olarak güncelle
        araba.setMusait(false);
        arabaRepository.save(araba);

        // 5. Kiralama kaydını veritabanına ekle
        return kiralamaRepository.save(kiralama);
    }
}