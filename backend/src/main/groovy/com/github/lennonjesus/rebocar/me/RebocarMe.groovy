package com.github.lennonjesus.rebocar.me

import com.github.lennonjesus.rebocar.me.entity.tutorial.Customer
import com.github.lennonjesus.rebocar.me.repository.tutorial.CustomerRepository
import com.github.lennonjesus.rebocar.me.setup.Bootstrap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class RebocarMe implements CommandLineRunner {

    @Autowired
    Bootstrap bootstrap

    @Autowired
    CustomerRepository customerRepository

    public static void main(String[] args) {
        SpringApplication.run(RebocarMe.class, args);
    }

    @Override
    void run(String... strings) throws Exception {

        if(strings.find { it == "bootstrap" }){
            bootstrap.run()

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
}
