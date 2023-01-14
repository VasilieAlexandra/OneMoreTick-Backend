package com.app.onemoretick.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Email
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 45)
    @Size(min = 6, max = 45)
    private String password;

    @OneToMany(mappedBy = "idUser")
    private Set<Task> tasks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUserList")
    private Set<ShoppingList> shoppingLists = new LinkedHashSet<>();

}