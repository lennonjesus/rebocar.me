package com.github.lennonjesus.rebocar.me.setup

import com.github.lennonjesus.rebocar.me.entity.tutorial.Customer
import com.github.lennonjesus.rebocar.me.repository.tutorial.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BootstrapImpl implements Bootstrap {

    @Autowired
    CustomerRepository customerRepository

    void run(){
        customerRepository.deleteAll()

        customerRepository.save(new Customer(firstName: "Felipe", lastName: "Carvalho"))
        customerRepository.save(new Customer(firstName: "Rodrigo", lastName: "Carvalho"))
        customerRepository.save(new Customer(firstName: "Gisele", lastName: "Paiva"))
    }
}
