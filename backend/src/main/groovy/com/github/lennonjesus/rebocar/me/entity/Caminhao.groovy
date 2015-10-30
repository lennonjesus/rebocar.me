package com.github.lennonjesus.rebocar.me.entity

import org.springframework.data.annotation.Id
import org.springframework.data.geo.Point
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Caminhao {

    @Id
    String id
    String placa

    @GeoSpatialIndexed
    Point posicao // @see http://mgt6.github.io/2014/07/04/spring-data-mongodb-geosearch.html
}
