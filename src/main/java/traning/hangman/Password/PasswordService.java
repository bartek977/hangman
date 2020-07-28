package traning.hangman.Password;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class PasswordService {
    private PasswordRepository repository;

    PasswordService(PasswordRepository passwordRepository) {
        this.repository = passwordRepository;
    }

    Password getRandomPassword() {
        List<Password> passwords = repository.findAll();

        int randomNumber = (int)(Math.random()*passwords.size());

        return passwords.get(randomNumber);
    }
}
