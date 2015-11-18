package com.github.lennonjesus.rebocar.me.endpoint

import com.github.lennonjesus.rebocar.me.entity.Caminhao
import com.github.lennonjesus.rebocar.me.repository.CaminhaoRepository
import com.github.lennonjesus.rebocar.me.testhelpers.ResponseParser
import com.google.gson.Gson
import spock.lang.Specification

import javax.ws.rs.core.MultivaluedHashMap
import javax.ws.rs.core.MultivaluedMap
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

/**
 * Created by felipe on 18/11/15.
 */
class CaminhaoEndpointSpec extends Specification {

    CaminhaoEndpoint endpoint
    CaminhaoRepository repositoryMock

    def setup(){
        endpoint = new CaminhaoEndpoint()

        repositoryMock = Mock()
        endpoint.caminhaoRepository = repositoryMock
    }

    def "quando a localizacao atual nao eh informada, 422 eh retornado"(){
        given:
        UriInfo uriInfoMock = Mock()
        uriInfoMock.queryParameters >> new MultivaluedHashMap<String, String>()

        when:
        ResponseParser response = new ResponseParser(endpoint.findAll(uriInfoMock))

        then:
        422 == response.status
        "Parameters 'lat' and 'lng' are required when executing a search." == response.attrs.error
    }

    def "quando a latitude atual nao eh informada, 422 eh retornado"(){
        given:
        UriInfo uriInfoMock = Mock()
        MultivaluedMap<String, Double> params = new MultivaluedHashMap<String, Double>()

        params.add("lng", 1)
        uriInfoMock.queryParameters >> params

        when:
        ResponseParser response = new ResponseParser(endpoint.findAll(uriInfoMock))

        then:
        422 == response.status
        "Parameters 'lat' and 'lng' are required when executing a search." == response.attrs.error
    }

    def "quando a longitude atual nao eh informada, 422 eh retornado"(){
        given:
        UriInfo uriInfoMock = Mock()
        MultivaluedMap<String, Double> params = new MultivaluedHashMap<String, Double>()

        params.add("lat", 1)
        uriInfoMock.queryParameters >> params

        when:
        ResponseParser response = new ResponseParser(endpoint.findAll(uriInfoMock))

        then:
        422 == response.status
        "Parameters 'lat' and 'lng' are required when executing a search." == response.attrs.error
    }

    def "quando latitude e longitude sao informados, o endpoint retorna o que vem do repository"(){
        given:
        UriInfo uriInfoMock = Mock()
        MultivaluedMap<String, Double> params = new MultivaluedHashMap<String, Double>()

        params.add("lat", 1)
        params.add("lng", 1)
        uriInfoMock.queryParameters >> params

        when:
        ResponseParser response = new ResponseParser(endpoint.findAll(uriInfoMock))

        then:
        200 == response.status
        !response.attrs.error
        1 * repositoryMock.findAllByPosicaoNear(*_) >> [
                new Caminhao(placa: "a", posicao: [1, 2]),
                new Caminhao(placa: "b", posicao: [3, 4])
        ]
        [[placa:"a", posicao:[lat:1.0, lng:2.0]], [placa:"b", posicao:[lat:3.0, lng:4.0]]] == response.attrs.caminhoes
    }

}
