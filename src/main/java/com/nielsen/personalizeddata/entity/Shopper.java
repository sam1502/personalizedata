package com.nielsen.personalizeddata.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "shopper")
@Data
public class Shopper {

    @Id
    @Column(name = "shopper_id")
    private String shopperId;
}
