package io.oauth2.learn.model;

import lombok.Data;

import javax.persistence.*;

import java.util.Set;

@Entity
@Data
@Table(name = "product_manager")
public class ProductManager {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "p_id")
    private Long pId;
    private String userName;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinTable(name = "join_product_manager_role",joinColumns = {@JoinColumn(name="p_id")},inverseJoinColumns = {@JoinColumn(name="r_id")})
    private Set<ProductManagerRole> roles;
}
