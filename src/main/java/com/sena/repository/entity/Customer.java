package com.sena.repository.entity;

import lombok.*;

import javax.persistence.*;

/*
 * The entity path should be added to the hibernate.cfg file.
 */

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_id",
            sequenceName = "sq_customer_id",
            initialValue = 10, allocationSize = 5)
    @GeneratedValue(generator = "sq_customer_id")
    long id;

    // varchar -> default lenght = 255
    @Column(name = "customer_name", length = 100, nullable = false)
    String name;

    @Column(name = "customer_lastname", length = 100)
    String lastname;

    @Column(name = "customer_address", length = 500)
    String address;

    public Customer(String name, String lastname, String address) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
    }

}
