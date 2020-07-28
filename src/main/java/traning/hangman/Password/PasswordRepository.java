package traning.hangman.Password;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import traning.hangman.User.User;

import java.util.List;

@Repository
interface PasswordRepository extends JpaRepository<Password, Integer> {

    @Query(value = "SELECT * FROM passwords WHERE passwords.id NOT IN (SELECT id FROM passwords JOIN games ON passwords.id=games.password_id where games.user_id=?1)", nativeQuery = true)
    List<Password> findPasswordsNotPlayedByUser(Integer userId);
}
