package com.siggebig.demo.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "instructions")
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String instruction;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

}
