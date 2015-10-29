package com.github.lennonjesus.rebocar.me.repository

import com.github.lennonjesus.rebocar.me.entity.Caminhao
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by felipe on 10/29/15.
 */
interface CaminhaoRepository extends MongoRepository<Caminhao, Long> {
}
