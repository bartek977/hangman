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

    @ManyToMany(fetch= FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="games",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="password_id")
    )
    private List<Password> playedPasswords;
}
