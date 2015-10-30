package com.github.lennonjesus.rebocar.me.repository.tutorial

import com.github.lennonjesus.rebocar.me.entity.tutorial.Customer
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @see https://spring.io/guides/gs/accessing-data-mongodb/
 */
interface CustomerRepository extends MongoRepository<Customer, String> {
    public Customer findByFirstName(String firstName)
    public List<Customer> findByLastName(String lastName)
}
