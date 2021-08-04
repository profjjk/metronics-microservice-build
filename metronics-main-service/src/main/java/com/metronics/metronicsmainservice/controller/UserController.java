package com.metronics.metronicsmainservice.controller;

import com.metronics.metronicsmainservice.dto.Authorities;
import com.metronics.metronicsmainservice.dto.Users;
import com.metronics.metronicsmainservice.repository.AuthoritiesRepository;
import com.metronics.metronicsmainservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthoritiesRepository authRepo;

    @PostMapping("/api/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Users createNewAdmin(@RequestBody Users users) {
        Authorities auth1 = new Authorities();
        Authorities auth2 = new Authorities();


        PasswordEncoder enc = new BCryptPasswordEncoder();
        users.setPassword(enc.encode(users.getPassword()));
        users.setEnabled(true);
        Users newUser = userRepository.save(users);


        auth1.setUserId(newUser.getId());
        auth1.setUsername(newUser.getUsername());
        auth1.setAuthority(Authorities.Authority.ADMIN);

        auth2.setUserId(newUser.getId());
        auth2.setUsername(newUser.getUsername());
        auth2.setAuthority(Authorities.Authority.CUSTOMER);


        System.out.println(authRepo.save(auth1));
        System.out.println(authRepo.save(auth2));
        return newUser;
    }

    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Users createNewUser(@RequestBody Users users) {
        Authorities auth2 = new Authorities();

        PasswordEncoder enc = new BCryptPasswordEncoder();
        users.setPassword(enc.encode(users.getPassword()));
        users.setEnabled(true);
        Users newUser = userRepository.save(users);

        auth2.setUserId(newUser.getId());
        auth2.setUsername(newUser.getUsername());
        auth2.setAuthority(Authorities.Authority.CUSTOMER);

        System.out.println(authRepo.save(auth2));
        return newUser;
    }

    @GetMapping("/api/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Users> getAllUsers() {
    return userRepository.findAll();
    }

    @GetMapping("/api/loggedIn")
    @ResponseStatus(HttpStatus.OK)
    public String isLoggedIn(Principal principal) {
        return principal.getName() + " is authenticated.";
    }
}
