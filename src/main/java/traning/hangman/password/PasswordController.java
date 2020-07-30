package traning.hangman.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import traning.hangman.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/passwords")
public class PasswordController {

    @Autowired
    private PasswordService service;
    @Autowired
    private HttpServletRequest request;

    public PasswordController() {}

    public PasswordController(PasswordService passwordService) {
        this.service = passwordService;
    }

    @GetMapping
    public ResponseEntity<Password> getPassword() {
        HttpSession session = request.getSession(true);
        User user;
        try {
            user = (User)session.getAttribute("user");
            return ResponseEntity.ok(service.getPassword(user.getId()));
        } catch (Exception ex) {
            System.out.println("function getPassword()");
            return(ResponseEntity.ok(new Password()));
        }
    }
}
