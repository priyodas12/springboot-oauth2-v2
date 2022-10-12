package io.oauth2.learn.model;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestResource
{
    @GetMapping("/oauth2/users/")
    public ResponseEntity<StockDetails> getUserInfo()
        {

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String email = user.getUsername() + "@yvl.com";

            StockDetails stockDetails = new StockDetails();

            stockDetails.setStockName(Faker.instance().company().name().toString());
            stockDetails.setStockPrice(String.valueOf(Faker.instance().random().nextDouble()));
            stockDetails.setStockBusinessEmail(email);

            log.info("stockDetails: {}",stockDetails);

            return new ResponseEntity<>(stockDetails,HttpStatus.OK);
        }
}