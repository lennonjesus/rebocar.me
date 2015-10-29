package com.github.lennonjesus.rebocar.me

import com.github.lennonjesus.rebocar.me.repository.RestaurantRepositoryImpl
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class RebocarMe {

    public static void main(String[] args) {
        SpringApplication.run(RebocarMe.class, args);

        RebocarMe app = new RebocarMe()
        app.retrieveAddresses()
    }

    private void retrieveAddresses(){
        new RestaurantRepositoryImpl().findAll()
    }
}
