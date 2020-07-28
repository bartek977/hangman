package traning.hangman.User;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUserByPointsAsc() {
        return null;
    }

    public boolean isRegisted(String name, String password) {

        Optional<User> result = userRepository.findByUsernameAndPassword(name, password);

        if(result.isPresent()) {
            return true;
        }
        else {
            //we didn't find user
            System.out.println("Did not find User - " + name + ":" + password);
            return false;
        }
    }
}
