package io.oauth2.learn.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long roleId;
    private String name;
    @ManyToMany(mappedBy = "userRoles")
    private Set<Users> usersSet;
}

