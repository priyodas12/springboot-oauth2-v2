package io.oauth2.learn.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_manager_roles")
public class ProductManagerRole {

    @Id
    //@GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "r_id")
    private Long rId;
    private String roleNames;
}
