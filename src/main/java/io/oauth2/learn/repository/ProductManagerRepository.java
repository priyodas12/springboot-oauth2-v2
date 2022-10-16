package io.oauth2.learn.repository;

import io.oauth2.learn.model.ProductManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductManagerRepository extends JpaRepository<ProductManager,Long> {
    ProductManager save(ProductManager productManager);
    ProductManager getProductManagerByUserName(String userName);
}
