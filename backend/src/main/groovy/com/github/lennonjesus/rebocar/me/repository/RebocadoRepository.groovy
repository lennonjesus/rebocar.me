package com.github.lennonjesus.rebocar.me.repository

import com.github.lennonjesus.rebocar.me.entity.Rebocado
import org.springframework.data.mongodb.repository.MongoRepository

interface RebocadoRepository extends MongoRepository<Rebocado, String> {
    Rebocado findByLogin(String login)
}