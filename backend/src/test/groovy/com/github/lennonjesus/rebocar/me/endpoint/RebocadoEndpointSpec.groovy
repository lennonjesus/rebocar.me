package com.github.lennonjesus.rebocar.me.endpoint

import com.github.lennonjesus.rebocar.me.entity.Rebocado
import com.github.lennonjesus.rebocar.me.repository.RebocadoRepository
import org.codehaus.jettison.json.JSONObject
import spock.lang.Specification

import javax.ws.rs.core.Response

/**
 * Created by felipe on 01/12/15.
 */
class RebocadoEndpointSpec extends Specification {

    RebocadoEndpoint endpoint
    RebocadoRepository rebocadoRepositoryMock

    def setup(){
        rebocadoRepositoryMock = Mock()

        endpoint = new RebocadoEndpoint(rebocadoRepository: rebocadoRepositoryMock)
    }

    def "create calls repository and returns 201 with a JSON representation of the new instance"() {
        given:
        JSONObject json = new JSONObject([login: "felipe", nome: "Felipe", email: "felipe@rebocar.me", celular: "992130056", endereco: "Rua das Laranjeiras", cep: "22270243"])

        when:
        Response response = endpoint.create(json)

        then:
        201 == response.status
        ["felipe"] == response.metadata.Location.path
        '{"login":"felipe","nome":"Felipe","email":"felipe@rebocar.me","celular":"992130056","endereco":"Rua das Laranjeiras","cep":"22270243"}' == response.entity
        1 * rebocadoRepositoryMock.save(_ as Rebocado) >> new Rebocado(login: "felipe", nome: "Felipe", email: "felipe@rebocar.me", celular: "992130056", endereco: "Rua das Laranjeiras", cep: "22270243")
    }

    def "create retorna 409 se ja existir um rebocado com o mesmo login"() {
        given:
        JSONObject json = new JSONObject([login: "felipe", nome: "Felipe", email: "felipe@rebocar.me", celular: "992130056", endereco: "Rua das Laranjeiras", cep: "22270243"])

        when:
        Response response = endpoint.create(json)

        then:
        409 == response.status
        '{"error":"Ja existe um usuario com o mesmo login"}' == response.entity
        0 * rebocadoRepositoryMock.save(_ as Rebocado)
        1 * rebocadoRepositoryMock.findByLogin("felipe") >> new Rebocado(login: "felipe", nome: "Felipe", email: "felipe@rebocar.me", celular: "992130056", endereco: "Rua das Laranjeiras", cep: "22270243")
    }
}
