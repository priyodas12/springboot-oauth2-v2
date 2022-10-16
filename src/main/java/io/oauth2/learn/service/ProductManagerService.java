package io.oauth2.learn.service;

import io.oauth2.learn.model.ProductManager;
import io.oauth2.learn.repository.ProductManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductManagerService implements UserDetailsService {

    @Autowired
    private ProductManagerRepository productManagerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ProductManager save(ProductManager productManager){
       log.info("ProductManagerService:save()...");
       productManager.setPassword(passwordEncoder.encode(productManager.getPassword()));
       return  productManagerRepository.save(productManager);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("loadUserByUsername....");
        ProductManager productManager = productManagerRepository.getProductManagerByUserName(userName);

        List<GrantedAuthority> authorityList = new ArrayList<>();
        productManager.getRoles().forEach(role -> {
            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(role.getRoleNames());
            authorityList.add(sga);
        });

        log.info("loadUserByUsername.... roles :{}", authorityList);
        UserDetails userDetails = User.withUsername(productManager.getUserName())
                .password(productManager.getPassword())
                .authorities(authorityList).build();

        return userDetails;
    }
}
