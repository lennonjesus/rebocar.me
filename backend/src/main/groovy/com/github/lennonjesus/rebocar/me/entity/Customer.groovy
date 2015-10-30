package com.github.lennonjesus.rebocar.me.entity

import org.springframework.data.annotation.Id

/**
 * @see https://spring.io/guides/gs/accessing-data-mongodb/
 */
class Customer {

    @Id
    String id
    String firstName
    String lastName


    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
