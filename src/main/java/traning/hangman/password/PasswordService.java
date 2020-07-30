package traning.hangman.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasswordService {
    @Autowired
    private PasswordRepository repository;

    public PasswordService() {}

    PasswordService(PasswordRepository passwordRepository) {
        this.repository = passwordRepository;
    }

    Password getPassword(Integer userId) {
        List<Password> passwords = repository.findPasswordsNotPlayedByUser(userId);
        System.out.println(passwords);

        int randomNumber = (int)(Math.random()*passwords.size());

        return passwords.get(randomNumber);
    }

    public Optional<Password> findById(Integer id) {
        return repository.findById(id);
    }
}
