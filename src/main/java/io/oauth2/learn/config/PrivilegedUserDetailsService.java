package io.oauth2.learn.config;

import io.oauth2.learn.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import io.oauth2.learn.model.*;
import org.springframework.stereotype.Service;

@Service
public class PrivilegedUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomUserRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

       User user= (User) usersRepository.findByName(name);

       return new org.springframework.security.core.userdetails.User(
               user.getUsername(),user.getPassword(),user.getAuthorities());
    }
}
