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

    @PostMapping(value = "/api/admin")
    @ResponseStatus(value = HttpStatus.CREATED)
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

    @PostMapping(value = "/api/customers")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Users createNewCustomer(@RequestBody Users users) {
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

    @GetMapping(value = "/api/admin")
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<Users> getAllCustomers() {
    return userRepository.findAll();
    }

    @GetMapping(value = "/api/loggedIn")
    @ResponseStatus(value = HttpStatus.OK)
    public String isLoggedIn(Principal principal) {
        return principal.getName() + " is authenticated.";
    }




}
