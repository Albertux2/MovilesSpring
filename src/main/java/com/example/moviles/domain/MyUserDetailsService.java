package com.example.moviles.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.moviles.security.ApplicationUserRol.GUEST;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordConfig passwordConfig;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<ApplicationUser> byUsername = userRepository.findByUsername(username);
        if(byUsername.isPresent()){
           return byUsername.get();
        }
        throw new UsernameNotFoundException(username);
    }

    public UserDetails registerNewUser(UserDTO userDTO) throws Exception {
        if(userExistByName(userDTO.getUsername())){
            throw new Exception("User already exists");
        }
        if(!passwordConfirm(userDTO.getPassword(),userDTO.getPasswordConfirm())){
            throw new Exception("Password not equals");
        }
        ApplicationUser user = new ApplicationUser(userDTO.getUsername(),passwordConfig.passwordEncoder().encode(userDTO.getPassword()),GUEST.getGrantedAuthorities());
        return userRepository.save(user);
    }

    private boolean userExistByName(String username){
        return userRepository.findByUsername(username).isPresent();
    }

    private boolean passwordConfirm(String password,String passwordConfirm){
        return password.equals(passwordConfirm);
    }
}
