package com.spring.eventPlaning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assuming auto-generated IDs
    private Long id;

    @Column(nullable = false, unique = true) // Ensure role names are unique
    private String name;

}
