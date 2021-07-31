package com.metronics.metronicsmainservice.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class PasswordUtility {

    public static void main(String[] args) {
        //instantiates bcrypt
        PasswordEncoder enc = new BCryptPasswordEncoder();

        //sets password to string "password"
        String password = "password";

        //encoded the password
        String encodedPassword = enc.encode(password);

        //prints encoded password to the console
        System.out.println(encodedPassword);

    }

}