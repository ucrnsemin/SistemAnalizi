# Araç Kiralama Projesi

Bu proje, Java Spring Boot ve PostgreSQL kullanılarak geliştirilmiş basit bir araç kiralama sistemidir.

## Projeyi Çalıştırmak İçin Gerekenler
* Java 17 (veya üstü) JDK
* Apache Maven
* PostgreSQL veritabanı sunucusu

## Kurulum Adımları

Projeyi sorunsuz bir şekilde çalıştırmak için lütfen aşağıdaki adımları sırasıyla takip ediniz.

**1-Veritabanını Kurma**

*Bu proje PostgreSQL veritabanı kullanmaktadır.
Projenin çalışması için gereken tabloları ve başlangıç verilerini kurmak için:*

*Bir PostgreSQL yönetim aracı 
(pgAdmin, DBeaver vb.) veya psql komut satırını açın.*

*Proje ile birlikte gelen veritabani_yedek.sql dosyasını
bu aracı kullanarak çalıştırın. Bu komut dosyası, arac_kiralama_db adında yeni 
bir veritabanı oluşturacak, gerekli
tüm tabloları kuracak ve başlangıç verilerini ekleyecektir.*

**2- Uygulama Ayarlarını Yapılandırma**

*Projenin veritabanına bağlanabilmesi
için küçük bir ayar yapmanız gerekmektedir.*

*Proje klasöründeki src/main/resources/application.properties 
dosyasını bir metin editörü ile açın.*

*Aşağıdaki satırları kendi PostgreSQL kurulumunuza göre güncelleyin:*
```properties
spring.datasource.username=sizin_postgres_kullanici_adiniz
spring.datasource.password=sizin_postgres_şifreniz
```

**3- Uygulamayı Başlatma**

*Uygulamayı başlatmanın iki yolu vardır:*


*IDE ile: Projeyi IntelliJ IDEA gibi bir IDE'de açıp
AracKiralamaApplication.java dosyasını direkt çalıştırabilirsiniz.*

*Terminal ile: Proje dizininde bir terminal 
açın ve aşağıdaki Maven komutunu çalıştırın:*
```bash
mvn spring-boot:run
```

**4- Uygulamaya Erişim**

*Uygulama başarıyla başladıktan sonra,
bir web tarayıcısı açarak aşağıdaki adrese gidin:
http://localhost:8080*

*Uygulama sizi otomatik olarak giriş sayfasına yönlendirecektir.*

*Test Kullanıcıları
Admin:

Kullanıcı Adı: admin

Şifre: 12345

Normal Kullanıcı:

Kullanıcı Adı: user

Şifre: 123*














