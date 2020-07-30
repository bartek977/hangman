package traning.hangman.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import traning.hangman.password.Password;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    //logging
    Optional<User> findByUsernameAndPassword(String name, String password);

    //top 5 players
    List<User> findTop5ByOrderByPointsDesc();

    User findByUsername(String username);

    //reczne dodanie password do relacji manyToMany, poniewaz przy updacie usera
    // (po dodaniu nowego passwordu do listy(dokładnie to drugiego, przy pierwszym jest okej)
    // wywala błąd klucza głównego/indeksu unique
    // wygląda jakby spring próbował dodać całą liste passwordów do bazy a nie tylko nowy element
    @Modifying
    @Query(value = "INSERT INTO games (user_id, password_id) values (:user_id, :password_id)",
            nativeQuery = true)
    void addPasswordGameToUser(@Param("user_id") Integer userId, @Param("password_id") Integer passwordId);

}
