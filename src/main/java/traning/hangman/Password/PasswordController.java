package traning.hangman.Password;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/passwords")
public class PasswordController {

    private PasswordService service;

    public PasswordController(PasswordService passwordService) {
        this.service = passwordService;
    }

    @GetMapping
    public ResponseEntity<Password> getPassword() {
        return ResponseEntity.ok(service.getRandomPassword());
    }
}
