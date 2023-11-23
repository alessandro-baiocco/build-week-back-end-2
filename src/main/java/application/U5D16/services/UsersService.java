package application.U5D16.services;

import application.U5D16.config.EmailSender;
import application.U5D16.entities.Client;
import application.U5D16.entities.User;
import application.U5D16.exceptions.NotFoundException;
import application.U5D16.payloads.user.EmailDTO;
import application.U5D16.repositories.UsersRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EmailSender emailSender;
    @Autowired
    private Cloudinary cloudinary;


    public Page<User> getUsers(int page, int size, String orderBy ) {
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
        if (found.getAvatar().equals("http://ui-avatars.com/api/?name="+ found.getCognome().trim().replace(" " , "") + "+" + found.getNome().trim().replace(" " , ""))){
            found.setAvatar("http://ui-avatars.com/api/?name=" + found.getCognome().trim().replace(" " , "") + "+" + body.getNome().trim().replace(" ", ""));
        }


        return usersRepository.save(found);
    }

    public User findByEmail(String email){
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }

    public void sendEmail(EmailDTO body) throws IOException{
        emailSender.sendemail(body.recipient(), body.object(), body.contents());
    }

    public String imageUpload(UUID id, MultipartFile file) throws NotFoundException, IOException{
        User found = this.findById(id);
        String img = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(img);
        usersRepository.save(found);
        return found.getAvatar();
    }


}
