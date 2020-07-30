package traning.hangman.hangman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import traning.hangman.password.Password;
import traning.hangman.password.PasswordService;
import traning.hangman.user.CrmUser;
import traning.hangman.user.User;
import traning.hangman.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private UserService userService;
    private PasswordService passwordService;
    private HttpServletRequest request;

    HomeController(UserService userService, PasswordService passwordService, HttpServletRequest request) {

        this.userService = userService;
        this.passwordService = passwordService;
        this.request = request;
    }

    @GetMapping("/")
    public String homePage() {
        return "index.html";
    }

    @GetMapping("/game")
    public String login() {
            return "game.html";
    }

    @GetMapping(value = "api/top")
    @ResponseBody
    public List<User> getTop5Player() {
        System.out.println("pobieram top players");
        System.out.println(userService.getUserByPointsAsc());
        return userService.getUserByPointsAsc();
    }

    @GetMapping("/add-password-form")
    public String addPasswordForm() {
        return "add-password-form";
    }

    @PostMapping("/add-password-form")
    public String addPasswordForm(@RequestParam("password") String password) {
        Password thePassword = new Password();
        thePassword.setPassword(password);
        passwordService.save(thePassword);
        return "add-password-form-accepted";
    }

    @GetMapping("api/games")
    @ResponseStatus(value = HttpStatus.OK)
    public void addPoints(@RequestParam("passwordId") Integer passwordId) {
        HttpSession session = request.getSession(true);
        System.out.println("addPoints z passwordID=" + passwordId);

        User user = null;

        try {
            User temp = (User)session.getAttribute("user");
            user = userService.findByUsername(temp.getUsername());
        } catch (Exception ex) {
            System.out.println("function addPoints()");
        }
        finally {
            if(user!=null) {
                Optional<Password> password = passwordService.findById(passwordId);
                try {
                    System.out.println("\n\n\nprzed dodaniem");
                    System.out.println(user.getGames());
                    System.out.println("\n\n\n");
                } catch(Exception ex) {
                    System.out.println("EXCEPTION");
                }

                if(password.isPresent()) {
                    if(user.getGames() == null
                            ||user.getGames().isEmpty()
                            ||!user.getGames().contains(password.get())) {

                        //patrz -->UserRepository::addPasswordGameToUser()
                        user.addGame(password.get());
                        //userService.addPasswordGameToUser(user.getId(), passwordId);
                        user.setPoints(user.getPoints()+1);
                        System.out.println("\n\n\nprzed savem");
                        System.out.println(user.getGames());
                        System.out.println("\n\n\n");
                        userService.update(user);
                        session.setAttribute("user", user);
                        System.out.println("\n\n\npo savie");
                        System.out.println(user.getGames());
                        System.out.println("\n\n\n");
                    }
                }
                else {
                    System.out.println("Nie ma takiego passworda");
                }
            }
        }


    }

}
