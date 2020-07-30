package traning.hangman.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import traning.hangman.role.Role;
import traning.hangman.role.RoleRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl() {}

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository
            , BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUserByPointsAsc() {
        return userRepository.findTop5ByOrderByPointsDesc();
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

    public boolean isUserExist(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void addPasswordGameToUser(Integer userId, Integer passwordId) {
        userRepository.addPasswordGameToUser(userId, passwordId);
    }

    @Override
    public User findByUsername(String userName) {
        // check the database if the user already exists
        return userRepository.findByUsername(userName);
    }

    @Override
    public void save(CrmUser crmUser) {
        User user = new User();
        // assign user details to the user object
        user.setUsername(crmUser.getUserName());
        user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
        user.setPoints(0);

        // give user default role
        List<Role> defaultRoles = new ArrayList<>();
        defaultRoles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(defaultRoles);

        // save user in the database
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
