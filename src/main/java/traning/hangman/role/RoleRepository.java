package traning.hangman.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    <T> T findByName(String role_user);
}
