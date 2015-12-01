package com.github.lennonjesus.rebocar.me.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class PrestadorServico extends Usuario {

    @Id
    String id
    String numeroFixo
    Caminhao caminhao
}
