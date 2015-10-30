package com.github.lennonjesus.rebocar.me.repository

import com.github.lennonjesus.rebocar.me.entity.Caminhao
import org.springframework.data.mongodb.repository.MongoRepository

interface CaminhaoRepository extends MongoRepository<Caminhao, Long> {
}
