package com.github.lennonjesus.rebocar.me.repository

import com.github.lennonjesus.rebocar.me.entity.Caminhao
import org.springframework.data.geo.Distance
import org.springframework.data.geo.Point
import org.springframework.data.mongodb.repository.MongoRepository

interface CaminhaoRepository extends MongoRepository<Caminhao, String> {
    List<Caminhao> findAllByPosicaoNear(Point posicaoAtual, Distance distance)
}
