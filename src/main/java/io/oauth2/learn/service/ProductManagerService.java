package io.oauth2.learn.service;

import io.oauth2.learn.model.ProductManager;
import io.oauth2.learn.repository.ProductManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductManagerService implements UserDetailsService {

    @Autowired
    private ProductManagerRepository productManagerRepository;

    public ProductManager save(ProductManager productManager){
       log.info("ProductManagerService:save()...");

       return  productManagerRepository.save(productManager);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
     log.info("loadUserByUsername....");
     ProductManager productManager= productManagerRepository.getProductManagerByUserName(s);

      UserDetails userDetails= User.withUsername(productManager.getUserName())
              .password(productManager.getPassword())
              .authorities("ADMIN").build();

      return userDetails;
    }
}
