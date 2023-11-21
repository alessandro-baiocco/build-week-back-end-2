package application.U5D16.services;

import application.U5D16.config.EmailSender;
import application.U5D16.entities.User;
import application.U5D16.exceptions.NotFoundException;
import application.U5D16.payloads.user.EmailDTO;
import application.U5D16.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EmailSender emailSender;


    public Page<User> getUsers(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return usersRepository.findAll(pageable);
    }

    public User findById(UUID id) throws NotFoundException {
        return usersRepository.findById(id).orElseThrow( ()  -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException{
        User found = this.findById(id);
        usersRepository.delete(found);
    }

    public User findByIdAndUpdate(UUID id, User body) throws NotFoundException{
        User found = this.findById(id);
        found.setUsername(body.getUsername());
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        return usersRepository.save(found);
    }

    public User findByEmail(String email){
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }

    public void sendEmail(EmailDTO body) throws IOException{
        emailSender.sendemail(body.recipient(), body.object(), body.contents());
    }
}
