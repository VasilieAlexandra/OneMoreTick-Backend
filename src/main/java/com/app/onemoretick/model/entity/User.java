package com.app.onemoretick.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 45)
    @Size(min = 6, max = 45)
    private String password;

    @OneToMany(mappedBy = "idUser")
    @JsonIgnore
    private Set<Task> tasks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUserList")
    @JsonIgnore
    private Set<ShoppingList> shoppingLists = new LinkedHashSet<>();

}