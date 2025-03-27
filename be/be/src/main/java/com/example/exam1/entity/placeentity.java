package com.example.exam1.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "places")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class placeentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    private double rating;
}
