package com.github.lennonjesus.rebocar.me.entity

import com.fasterxml.jackson.core.JsonGenerator
import org.springframework.data.annotation.Id
import org.springframework.data.geo.Point
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Caminhao implements SelfMarshallingEntity {

    @Id
    String id
    String placa

    @GeoSpatialIndexed
    Point posicao // @see http://mgt6.github.io/2014/07/04/spring-data-mongodb-geosearch.html

    @Override
    void asJson(JsonGenerator g) {
        g.writeStartObject()

        g.writeStringField("placa", placa)

        g.writeObjectFieldStart("posicao")
            g.writeNumberField("lat", posicao.x)
            g.writeNumberField("lng", posicao.y)
        g.writeEndObject()

        g.writeEndObject()
    }
}
