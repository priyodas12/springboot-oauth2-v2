package io.oauth2.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import io.oauth2.learn.model.*;

public interface CustomUserRepository<User> extends CrudRepository<User,Long> {

    User findUsersById(Long id);

    User findByName(String name);
}
