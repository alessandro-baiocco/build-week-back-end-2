package application.U5D16.controllers;

import application.U5D16.entities.User;
import application.U5D16.payloads.user.EmailDTO;
import application.U5D16.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;


    @GetMapping("")
    public Page<User> getAllUser(@RequestParam(defaultValue = "0")int page ,
                                 @RequestParam(defaultValue = "10")int size,
                                 @RequestParam(defaultValue = "id")String order){
        return usersService.getUsers(page , size , order);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable UUID id){
        return usersService.findById(id);
    }


    @GetMapping("/me")
    public UserDetails getProfile(@AuthenticationPrincipal UserDetails currentUser){
        return currentUser;
    };



    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDetails getProfile(@PathVariable UUID id, @RequestBody User body){
        return usersService.findByIdAndUpdate(id , body);
    }


    @PatchMapping("/upload/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String uploadPicture(@PathVariable UUID id, @RequestParam("avatar") MultipartFile file) throws IOException {
        return usersService.imageUpload(id, file);
    }



    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id){
        usersService.findByIdAndDelete(id);
    }


    @PostMapping("/sendEmail")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void sendEmail(@RequestBody EmailDTO body) throws IOException{
         usersService.sendEmail(body);
    }


}
