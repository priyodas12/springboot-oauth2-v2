package io.oauth2.learn.controller;

import io.oauth2.learn.model.ProductManager;
import io.oauth2.learn.service.ProductManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductManagerController {

    @Autowired
    private ProductManagerService productManagerService;

    @PostMapping("/product/managers")
    public ResponseEntity<ProductManager> save(@RequestBody ProductManager productManager){

        log.info("ProductManagerController:save()...");

        ProductManager productManager1=productManagerService.save(productManager);

        return new ResponseEntity<>(productManager1,HttpStatus.CREATED);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> pingMe(){
        log.info("ProductManagerController:pingMe()...");

        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
