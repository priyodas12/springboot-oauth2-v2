package io.oauth2.learn.model;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long roleId;
    private String name;
    @ManyToMany(mappedBy = "userRoles")
    private Set<User> usersSet;

    @Override
    public String getAuthority() {
        return name;
    }
}

