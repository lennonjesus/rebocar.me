package com.github.lennonjesus.rebocar.me.entity

import org.springframework.data.annotation.Id

class Caminhao {

    @Id
    Long id
    String placa
    Double lat
    Double lng
    Boolean rebocaCarro
    Boolean rebocaMoto
}
