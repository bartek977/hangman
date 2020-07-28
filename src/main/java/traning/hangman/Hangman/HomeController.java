package traning.hangman.Hangman;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import traning.hangman.User.UserService;

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

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        System.out.println("jestem");
        if(userService.isRegisted(username,password)) {
            System.out.println("powinno zalogowac");
            return "redirect:/game.html";
        }
        else
            return "redirect:/index.html";
    }
}
