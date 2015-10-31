package com.github.lennonjesus.rebocar.me.setup

import com.github.lennonjesus.rebocar.me.entity.Caminhao
import com.github.lennonjesus.rebocar.me.entity.tutorial.Customer
import com.github.lennonjesus.rebocar.me.repository.CaminhaoRepository
import com.github.lennonjesus.rebocar.me.repository.tutorial.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component

@Component
class BootstrapImpl implements Bootstrap {

    @Autowired
    CustomerRepository customerRepository

    @Autowired
    CaminhaoRepository caminhaoRepository

    void run(){
        customerRepository.deleteAll()
        caminhaoRepository.deleteAll()

        customerRepository.save(new Customer(firstName: "Felipe", lastName: "Carvalho"))
        customerRepository.save(new Customer(firstName: "Rodrigo", lastName: "Carvalho"))
        customerRepository.save(new Customer(firstName: "Gisele", lastName: "Paiva"))

        caminhaoRepository.save(new Caminhao(placa: "Maracana", posicao: [-22.9121039,-43.2323445]))
        caminhaoRepository.save(new Caminhao(placa: "Casa do Alemao - Jd Primavera", posicao: [-22.6949728,-43.2898208]))
        caminhaoRepository.save(new Caminhao(placa: "Praia do Leblon", posicao: [-22.9878367,-43.2235188]))
    }
}
