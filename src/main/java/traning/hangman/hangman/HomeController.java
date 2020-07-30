package traning.hangman.hangman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import traning.hangman.password.Password;
import traning.hangman.password.PasswordService;
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

    @GetMapping("api/games")
    @ResponseStatus(value = HttpStatus.OK)
    public void addPoints(@RequestParam("passwordId") Integer passwordId) {
        HttpSession session = request.getSession(true);
        System.out.println("addPoints z passwordID=" + passwordId);

        User user = null;

        try {
            user = (User)session.getAttribute("user");
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
                            ||user.getGames().get(user.getGames().size()-1).getId()!=password.get().getId()) {

                        //patrz -->UserRepository::addPasswordGameToUser()
                        //user.addGame(password.get());
                        userService.addPasswordGameToUser(user.getId(), passwordId);
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
