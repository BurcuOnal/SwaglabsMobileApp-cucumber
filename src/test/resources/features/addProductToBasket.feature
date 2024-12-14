@US02
Feature: Sepete Urun Ekleme Cikarma Testi
  Background:
    When Konum izni erişimi ver butonuna tıklanır
    And Uygulamayı kullanırken izin ver seçilir
    Then Menü ve Yardım Butonlarının tanıtımları geçilir
    Then Bildirim Tercihleri Belirlenir
    Then Teslimat Yöntemi Mağazadan Alacağım Seçilir
    Then Rastgele Teslimat Şehri Seçilir
    Then Rastgele Teslimat İlçesi Seçilir
    And  Rastgele Teslimat Magazasi Seçilir ve seçilmiş olarak görünen magaza ile aynı olduğu doğrulanır
    Then Neye ihtiyacin var? alanına tıklanir

  @UrunEkleme
  Scenario: Sepete Urun Eklenebilmeli
    Then Arama alaninda "kahve" aratilir
    Then İlgili Sonuçlardan rastgele bir seçim yapilir
    And Arama sonuçlarından herhangi biri sepete eklenir ve ürün fiyatı ile sepetteki fiyatın aynı olduğu doğrulanır
