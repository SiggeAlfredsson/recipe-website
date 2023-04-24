// package com.siggebig.demo.model;

// import jakarta.persistence.*;
// import lombok.Getter;
// import lombok.Setter;

// @Getter
// @Setter
// @Entity
// @Table(name = "ratings")
// public class Rating {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long id;

//     private String comment;

//     private int rating;

//     @ManyToOne
//     @JoinColumn(name = "recipe_id")
//     private Recipe recipe;
// }
