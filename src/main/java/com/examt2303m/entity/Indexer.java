package com.examt2303m.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "indexer")
    public class Indexer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_id")
    private Integer indexId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "valueMin", nullable = false)
    private Float valueMin;

    @Column(name = "valueMax", nullable = false)
    private Float valueMax;

}
