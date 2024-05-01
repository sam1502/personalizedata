package com.nielsen.personalizeddata.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "shopper_personalized_product")
@Data
public class ShopperPersonalizeProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopperId")
    private Shopper shopper;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @Column(name = "relevancy_score")
    private double relevancyScore;
}
