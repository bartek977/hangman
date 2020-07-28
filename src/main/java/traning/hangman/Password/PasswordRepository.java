package traning.hangman.Password;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PasswordRepository extends JpaRepository<Password, Integer> {
}
