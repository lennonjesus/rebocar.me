package com.github.lennonjesus.rebocar.me

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
        bootstrap.run()
    }
}
