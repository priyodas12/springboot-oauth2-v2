package io.oauth2.learn.controller;

import io.oauth2.learn.model.ProductManager;
import io.oauth2.learn.service.ProductManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductOwnerController {

    @Autowired
    private ProductManagerService productManagerService;

    @GetMapping("/product/owner/{id}")
    public ResponseEntity<List<String>> productOwnerInfo(@PathVariable Long pid){

        log.info("ProductOwnerController:productOwnerInfo() invoked");

        ProductManager productManager=productManagerService.fetchProductManagerInfo(pid);

        return new ResponseEntity<>(productManager.getProductOwner(), HttpStatus.OK);
    }
}
