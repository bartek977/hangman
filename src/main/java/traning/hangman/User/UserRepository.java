package traning.hangman.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    //logging
    Optional<User> findByUsernameAndPassword(String name, String password);

    //top 5 players
    List<User> findTop5ByOrderByPointsDesc();
}
