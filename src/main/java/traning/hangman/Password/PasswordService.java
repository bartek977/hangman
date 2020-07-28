package traning.hangman.Password;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class PasswordService {
    private PasswordRepository repository;

    PasswordService(PasswordRepository passwordRepository) {
        this.repository = passwordRepository;
    }

    Password getRandomPassword() {
        List<Password> passwords = repository.findPasswordsNotPlayedByUser(1);
        System.out.println(passwords);

        int randomNumber = (int)(Math.random()*passwords.size());

        return passwords.get(randomNumber);
    }
}
