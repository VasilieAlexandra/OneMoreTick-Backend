package com.app.onemoretick.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @OneToMany(mappedBy = "idCategory")
    @JsonIgnore
    @Column(nullable = true)
    @ToString.Exclude
    private Set<Task> tasks = new LinkedHashSet<>();


}