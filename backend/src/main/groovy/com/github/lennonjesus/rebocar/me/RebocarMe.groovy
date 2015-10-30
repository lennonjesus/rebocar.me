package com.github.lennonjesus.rebocar.me

import com.github.lennonjesus.rebocar.me.entity.Customer
import com.github.lennonjesus.rebocar.me.repository.CustomerRepository
import com.github.lennonjesus.rebocar.me.repository.RestaurantRepositoryImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class RebocarMe implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository

    public static void main(String[] args) {
        SpringApplication.run(RebocarMe.class, args);
    }

    @Override
    void run(String... strings) throws Exception {
        customerRepository.deleteAll()

        customerRepository.save(new Customer(firstName: "Felipe", lastName: "Carvalho"))
        customerRepository.save(new Customer(firstName: "Rodrigo", lastName: "Carvalho"))
        customerRepository.save(new Customer(firstName: "Gisele", lastName: "Paiva"))

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Felipe'):");
        System.out.println("--------------------------------");
        System.out.println(customerRepository.findByFirstName("Felipe"));

        System.out.println("Customers found with findByLastName('Carvalho'):");
        System.out.println("--------------------------------");
        for (Customer customer : customerRepository.findByLastName("Carvalho")) {
            System.out.println(customer);
        }
    }
}
