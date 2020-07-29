package traning.hangman.User;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import traning.hangman.Password.Password;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;
    private String username;
    private String password;
    private Integer points;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", points=" + points +
                '}';
    }
}
