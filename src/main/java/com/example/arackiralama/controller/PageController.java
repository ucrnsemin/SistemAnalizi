package com.example.arackiralama.controller;

import com.example.arackiralama.entity.Araba;
import com.example.arackiralama.entity.Kiralama;
import com.example.arackiralama.entity.Kullanici;
import com.example.arackiralama.entity.Rol;
import com.example.arackiralama.repository.ArabaRepository;
import com.example.arackiralama.repository.KullaniciRepository;
import com.example.arackiralama.repository.RolRepository;
import com.example.arackiralama.service.ArabaService;
import com.example.arackiralama.service.KiralamaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class PageController {

    // --- Gerekli servis ve repository'ler ---
    private final KullaniciRepository kullaniciRepository;
    private final RolRepository rolRepository;
    private final ArabaService arabaService;
    private final KiralamaService kiralamaService;
    private final ArabaRepository arabaRepository;


    public PageController(KullaniciRepository kullaniciRepository,
                          RolRepository rolRepository,
                          ArabaService arabaService,
                          KiralamaService kiralamaService,
                          ArabaRepository arabaRepository) {
        this.kullaniciRepository = kullaniciRepository;
        this.rolRepository = rolRepository;
        this.arabaService = arabaService;
        this.kiralamaService = kiralamaService;
        this.arabaRepository = arabaRepository;
    }


    @GetMapping("/")
    public String showHomePage() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        Optional<Kullanici> kullaniciOptional = kullaniciRepository.findByUsername(username);
        if (kullaniciOptional.isPresent()) {
            Kullanici kullanici = kullaniciOptional.get();
            if (password.equals(kullanici.getPassword())) {
                if (kullanici.getRoller().stream().anyMatch(rol -> rol.getName().equals("ADMIN"))) {
                    return "redirect:/admin";
                } else {
                    return "redirect:/user";
                }
            }
        }
        redirectAttributes.addFlashAttribute("error", "Geçersiz kullanıcı adı veya şifre!");
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("kullanici", new Kullanici());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute Kullanici kullanici, RedirectAttributes redirectAttributes) {
        Rol userRol = rolRepository.findByName("USER");
        kullanici.setRoller(new HashSet<>(Set.of(userRol)));
        kullaniciRepository.save(kullanici);
        redirectAttributes.addFlashAttribute("success", "Kayıt başarılı! Lütfen giriş yapın.");
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("arabalar", arabaService.findAll());
        model.addAttribute("yeniAraba", new Araba());
        return "admin";
    }

    @GetMapping("/user")
    public String showUserPage(Model model) {
        model.addAttribute("arabalar", arabaService.findAll());
        return "user";
    }




    // Kiralama formunu gösterecek metot
    @GetMapping("/kirala/{arabaId}")
    public String showKiralamaFormu(@PathVariable Long arabaId, Model model) {
        Araba araba = arabaRepository.findById(arabaId)
                .orElseThrow(() -> new IllegalStateException("Araba bulunamadı."));
        model.addAttribute("araba", araba);
        model.addAttribute("kiralama", new Kiralama());
        return "kirala"; // kirala.html'i gösterir
    }

    // Kiralama formundan gelen veriyi işleyecek metot
    @PostMapping("/kirala")
    public String processKiralamaFormu(@ModelAttribute Kiralama kiralama, @RequestParam Long arabaId, RedirectAttributes redirectAttributes) {

        Kullanici mevcutKullanici = kullaniciRepository.findByUsername("user")
                .orElseThrow(() -> new IllegalStateException("Test kullanıcısı 'user' bulunamadı."));

        Araba kiralananAraba = arabaRepository.findById(arabaId)
                .orElseThrow(() -> new IllegalStateException("Araba bulunamadı."));

        kiralama.setKullanici(mevcutKullanici);
        kiralama.setAraba(kiralananAraba);

        try {
            kiralamaService.kirala(kiralama);
            redirectAttributes.addFlashAttribute("successMessage", "Tebrikler! Araç başarıyla kiralandı.");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Hata: " + e.getMessage());
        }

        return "redirect:/user";
    }

    // Yeni araba ekleme işlemi
    @PostMapping("/admin/araba-ekle")
    public String processArabaEkleme(@ModelAttribute Araba yeniAraba, RedirectAttributes redirectAttributes) {
        arabaService.save(yeniAraba);
        redirectAttributes.addFlashAttribute("successMessage", "Yeni araç başarıyla eklendi!");
        return "redirect:/admin";
    }


    // Admin ekleme işlemi
    @PostMapping("/admin/admin-ekle")
    public String processAdminEkleme(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        // 1. Yeni bir kullanıcı nesnesi oluştur
        Kullanici yeniAdmin = new Kullanici();
        yeniAdmin.setUsername(username);
        yeniAdmin.setPassword(password); // Şifreleme yok, demo için direkt kaydediyoruz

        // 2. "ADMIN" rolünü veritabanından bul
        Rol adminRol = rolRepository.findByName("ADMIN");
        if(adminRol == null) {
            // Bu durum olmamalı ama garantiye alalım
            adminRol = new Rol("ADMIN");
            rolRepository.save(adminRol);
        }

        // 3. Role'ü yeni kullanıcıya ata
        yeniAdmin.setRoller(new HashSet<>(Set.of(adminRol)));

        // 4. Yeni admin kullanıcısını kaydet
        kullaniciRepository.save(yeniAdmin);

        redirectAttributes.addFlashAttribute("successMessage", "Yeni admin '" + username + "' başarıyla eklendi!");
        return "redirect:/admin";
    }


    // Düzenleme sayfasını gösteren metot
    @GetMapping("/admin/araba-duzenle/{id}")
    public String showArabaDuzenleFormu(@PathVariable Long id, Model model) {
        Araba araba = arabaService.findById(id);
        model.addAttribute("araba", araba);
        return "araba-duzenle"; // araba-duzenle.html'i gösterir
    }


    // Düzenleme formundan gelen güncellemeyi işleyen metot
    @PostMapping("/admin/araba-guncelle")
    public String processArabaGuncelleme(@ModelAttribute Araba araba, RedirectAttributes redirectAttributes) {
        // JPA, gelen nesnenin ID'si olduğu için bunu bir INSERT değil, UPDATE olarak anlar.
        arabaService.save(araba);
        redirectAttributes.addFlashAttribute("successMessage", "Araç bilgileri başarıyla güncellendi!");
        return "redirect:/admin";
    }

    // Silme işlemini yapan metot
    @PostMapping("/admin/araba-sil/{id}")
    public String processArabaSilme(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        arabaService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Araç başarıyla silindi!");
        return "redirect:/admin";
    }


}