package traning.hangman.Hangman;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import traning.hangman.User.User;
import traning.hangman.User.UserService;

import java.util.List;

@Controller
public class HomeController {

    private UserService userService;

    HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage() {
        return "index.html";
    }

    @PostMapping("/game")
    public String login(@RequestParam String username, @RequestParam String password) {

        System.out.println("jestem");
        if(userService.isRegisted(username,password)) {
            System.out.println("powinno zalogowac");
            return "game.html";
        }
        else
            return "index.html";
    }

    @GetMapping(value = "api/top")
    @ResponseBody
    public List<User> getTop5Player() {
        System.out.println("pobieram top players");
        System.out.println(userService.getUserByPointsAsc());
        return userService.getUserByPointsAsc();
    }
}
