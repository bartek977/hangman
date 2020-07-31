# hangman
Hangman game

Aplikacja dostępna na heroku- https://wisielec-gra.herokuapp.com/

Projekt w ramach przećwiczenia i utrwalenia wiedzy o budowie aplikacji webowej.
Wykorzystane technologie:
-Spring Boot
-Spring Security
-JPA
-baza H2
-Flyway
-Thymeleaf
-Lombok
-JavaScript
-HTML
-CSS

Początek projektu opierał się o kurs JS(https://www.youtube.com/watch?v=9FVtiJHFCSU&list=PLOYHgt8dIdoxTUYuHS9ZYNlcJq5R3jBsC&index=3). Miała to być prosta gra w wisielca pobierająca hasła z bazy danych.
W miarę dochodzenia nowych linijek kodu, przychodziły nowe pomysły m.in. dodania użytkowników, rankingu. Po zaimplementowaniu funkcjonalności rejestracji i logowania, zacząłem się zastanawiać nad obsługą sesji.
Myślałem, żeby wykorzystać ciasteczka i trzymać w nich informacje o userze, jednak ten sposób nie podobał mi się ze wzglęgdu na łatwą możliwość podszycia się pod innego użytkownika.
Wybrałem więc Spring Security. Bardzo łatwa implementacja, wiele funkcjonalności w tym obsługa sesji.

Podczas tworzenia aplikacji natrafiłem na kilka problemów, które zajęły mi trochę czasu, aby je rozwiązać jak również nie wszystkie zostały "rozpracowane" :)
Przy pobieraniu hasła przez api miałem problem z synchronicznością(zanim skrypt pobrał hasło z api kod wykonywał się dalej przez co występował błąd typu np. wywołanie metody toUpperCase na null). Problem udało się rozwiązać za pomocą async i await.
Po implementacji Spring Security, nie mogłem dostać się do h2 z powodu "request POST not supportet", po dodaniu odpowiedniego endopinta w SecurityConfig, zaczął się pojawiać panel logowania h2, jednak nie wyświetlała się strona do zarządzania bazą. Konsola przeglądarki podpowiedziała, że ramki(iframe) są zablokowane. Znowu wystarczyło dodać jedną metodą w Configu i zaczęło działać.
Duży problem(nierozwiązany do końca) napotkałem przy aktualizowaniu haseł rozegranych przez gracza. O ile obiekt javovy aktualizował się bez problemu(dodawanie rozgrywki do List/Set) o tyle przy aktualizacji db był problem.
Aplikacja próbowała dodać oprócz nowej rozgrywki również starą(ponownie!)- przez co wywalało błąd łamania klucza głównego(dwa takie same rekordy). Próbowałem obejść to NativeQuery, ale niestety bez efektu.
Finalnie usunąłem klucz główny, aplikacja działa jednak powiela rekordy.
