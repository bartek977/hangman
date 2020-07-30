package traning.hangman.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public User findByUsername(String userName);

    public void save(CrmUser crmUser);

    boolean isRegisted(String username, String password);

    List<User> getUserByPointsAsc();

    boolean isUserExist(String username);

    void update(User user);

    void addPasswordGameToUser(Integer userId, Integer passwordId);
}
