package traning.hangman.password;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import traning.hangman.user.User;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "passwords")
public class Password {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;
    private String password;


    public Password() {
    }

    @Override
    public String toString() {
        return "Password{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}