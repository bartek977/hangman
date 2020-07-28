package traning.hangman.Password;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import traning.hangman.User.User;

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

    @ManyToMany(fetch= FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="games",
            joinColumns=@JoinColumn(name="password_id"),
            inverseJoinColumns=@JoinColumn(name="user_id")
    )
    private List<User> users;

    public Password() {
    }
}