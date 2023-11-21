package application.U5D16.services;

import application.U5D16.config.EmailSender;
import application.U5D16.entities.User;
import application.U5D16.entities.enums.Role;
import application.U5D16.exceptions.BadRequestException;
import application.U5D16.exceptions.UnauthorizedException;
import application.U5D16.payloads.user.NewUserDTO;
import application.U5D16.payloads.user.UserLoginDTO;
import application.U5D16.repositories.UsersRepository;
import application.U5D16.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EmailSender emails;

    public String authenticateUser(UserLoginDTO body){
        // 1. Verifichiamo che l'email dell'utente sia nel db
        User user = usersService.findByEmail(body.email());

        // 2. In caso affermativo, verifichiamo se la password corrisponde a quella trovata nel db
        if(bcrypt.matches(body.password(), user.getPassword())) {
            // 3. Se le credenziali sono OK --> Genero un JWT e lo restituisco
            return jwtTools.createToken(user);
        } else {
            // 4. Se le credenziali NON sono OK --> 401
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public User registerUser(NewUserDTO body) throws IOException {

        // verifico se l'email è già utilizzata
        usersRepository.findByEmail(body.email()).ifPresent( user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });

        User newUser = new User();

        newUser.setNome(body.nome());
        newUser.setCognome(body.cognome());
        newUser.setUsername(body.nome() + "_" + body.cognome());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setEmail(body.email());
        newUser.setAvatar("http://ui-avatars.com/api/?name=" + body.cognome().trim().replace(" " , "") + "+" + body.nome().trim().replace(" " , ""));

        newUser.setRole(Role.USER);

        User savedUser = usersRepository.save(newUser);
        emails.sendRegistrationEmail(body.email(), body.nome());
        return savedUser;
    }
}
