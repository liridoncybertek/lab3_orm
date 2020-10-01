package com.cybertek.lab3_orm.service;

import com.cybertek.lab3_orm.model.AppUser;
import com.cybertek.lab3_orm.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService{


    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser createUser(AppUser appUser) {
        Optional<AppUser> checkIfExists = userRepository.findByEmail(appUser.getEmail());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return checkIfExists.orElse(userRepository.save(appUser));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser readAppUser = userRepository.findByEmail(email).orElse(null);
        if(readAppUser == null) {
            throw new UsernameNotFoundException(email);
        }
        return User.withUsername(readAppUser.getFirstName() + " " + readAppUser.getLastName())
                .password(readAppUser.getPassword())
                .authorities("USER").build();
    }
}
