package com.github.lennonjesus.rebocar.me.marshall

import com.github.lennonjesus.rebocar.me.entity.Caminhao
import org.springframework.data.geo.Point
import spock.lang.Specification

class JSONMarshallerSpec extends Specification {

    def "marshall returns an object called 'caminhoes' when collection has elements"() {
        when:
        def json = JSONMarshaller.marshall("caminhoes", [
                new Caminhao(placa: "a", posicao: new Point(1, 2)),
                new Caminhao(placa: "b", posicao: new Point(3, 4)),
        ])

        then:
        '{"caminhoes":[{"placa":"a","posicao":{"lat":1.0,"lng":2.0}},{"placa":"b","posicao":{"lat":3.0,"lng":4.0}}]}' == json
    }

    def "marshall returns an object called 'caminhoes' with an empty collection when it has no elements"() {
        when:
        def json = JSONMarshaller.marshall("caminhoes", [])

        then:
        '{"caminhoes":[]}' == json
    }

    def "marshall returns null if no collection name is provided"() {
        when:
        def json = JSONMarshaller.marshall(null, [
                new Caminhao(placa: "a", posicao: new Point(1, 2)),
                new Caminhao(placa: "b", posicao: new Point(3, 4)),
        ])

        then:
        null == json
    }

    def "marshallErrorMessage returns an object with 'error' attribute"() {
        when:
        def json = JSONMarshaller.marshallErrorMessage("my error message")

        then:
        '{"error":"my error message"}' == json
    }

    def "marshallErrorMessage returns an empty string with 'error' attribute if no message is provided"() {
        when:
        def json = JSONMarshaller.marshallErrorMessage("")

        then:
        '{"error":""}' == json
    }
}
