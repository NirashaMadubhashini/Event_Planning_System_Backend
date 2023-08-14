package com.spring.eventPlaning.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Packages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long packageId;
    private String packageName;
    private Long packagePrice;
    private String packageDuration;
    private String guestCount;
    private String listOfServices;

}
