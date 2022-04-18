package com.sena.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/*
 * The entity path should be added to the hibernate.cfg file.
 */

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    long customerId;
    Date salesDate;
    double price;
    int taxRate;
    double totalPrice;
}