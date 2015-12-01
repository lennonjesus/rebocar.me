package com.github.lennonjesus.rebocar.me.entity

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.JsonGenerator
import org.codehaus.jettison.json.JSONObject
import spock.lang.Specification

class RebocadoSpec extends Specification {

    StringWriter sw = null
    JsonFactory f = null
    JsonGenerator g = null

    def setup(){
        sw = new StringWriter()
        f = new JsonFactory()
        g = f.createJsonGenerator(sw)
    }

    def "rebocado eh serializado de acordo com seus atributos"(){
        given:
        Rebocado rebocado = new Rebocado(login: login, nome: nome, email: email, celular: celular, endereco: endereco, cep: cep)

        when:
        rebocado.asJson(g)
        g.close()

        then:
        json == sw.toString()

        where:
        nome     | login   | email               | celular     | endereco              | cep        | json
        "Felipe" | "login" | "felipe@rebocar.me" | "992130056" | "Rua das Laranjeiras" | "22270243" | '{"login":"login","nome":"Felipe","email":"felipe@rebocar.me","celular":"992130056","endereco":"Rua das Laranjeiras","cep":"22270243"}'
        null     | "login" | "felipe@rebocar.me" | "992130056" | "Rua das Laranjeiras" | "22270243" | '{"login":"login","email":"felipe@rebocar.me","celular":"992130056","endereco":"Rua das Laranjeiras","cep":"22270243"}'
        ""       | "login" | "felipe@rebocar.me" | "992130056" | "Rua das Laranjeiras" | "22270243" | '{"login":"login","email":"felipe@rebocar.me","celular":"992130056","endereco":"Rua das Laranjeiras","cep":"22270243"}'
        "Felipe" | null    | "felipe@rebocar.me" | "992130056" | "Rua das Laranjeiras" | "22270243" | '{"nome":"Felipe","email":"felipe@rebocar.me","celular":"992130056","endereco":"Rua das Laranjeiras","cep":"22270243"}'
        "Felipe" | ""      | "felipe@rebocar.me" | "992130056" | "Rua das Laranjeiras" | "22270243" | '{"nome":"Felipe","email":"felipe@rebocar.me","celular":"992130056","endereco":"Rua das Laranjeiras","cep":"22270243"}'
        "Felipe" | "login" | null                | "992130056" | "Rua das Laranjeiras" | "22270243" | '{"login":"login","nome":"Felipe","celular":"992130056","endereco":"Rua das Laranjeiras","cep":"22270243"}'
        "Felipe" | "login" | ""                  | "992130056" | "Rua das Laranjeiras" | "22270243" | '{"login":"login","nome":"Felipe","celular":"992130056","endereco":"Rua das Laranjeiras","cep":"22270243"}'
        "Felipe" | "login" | "felipe@rebocar.me" | null        | "Rua das Laranjeiras" | "22270243" | '{"login":"login","nome":"Felipe","email":"felipe@rebocar.me","endereco":"Rua das Laranjeiras","cep":"22270243"}'
        "Felipe" | "login" | "felipe@rebocar.me" | ""          | "Rua das Laranjeiras" | "22270243" | '{"login":"login","nome":"Felipe","email":"felipe@rebocar.me","endereco":"Rua das Laranjeiras","cep":"22270243"}'
        "Felipe" | "login" | "felipe@rebocar.me" | "992130056" | null                  | "22270243" | '{"login":"login","nome":"Felipe","email":"felipe@rebocar.me","celular":"992130056","cep":"22270243"}'
        "Felipe" | "login" | "felipe@rebocar.me" | "992130056" | ""                    | "22270243" | '{"login":"login","nome":"Felipe","email":"felipe@rebocar.me","celular":"992130056","cep":"22270243"}'
        "Felipe" | "login" | "felipe@rebocar.me" | "992130056" | "Rua das Laranjeiras" | null       | '{"login":"login","nome":"Felipe","email":"felipe@rebocar.me","celular":"992130056","endereco":"Rua das Laranjeiras"}'
        "Felipe" | "login" | "felipe@rebocar.me" | "992130056" | "Rua das Laranjeiras" | ""         | '{"login":"login","nome":"Felipe","email":"felipe@rebocar.me","celular":"992130056","endereco":"Rua das Laranjeiras"}'
    }

    def "fromJSON instancia Rebocado de acordo com os valores entrados"(){
        given:
        JSONObject jsonObject = new JSONObject(valores)

        expect:
        instancia == Rebocado.fromJSON(jsonObject)

        where:
        instancia                                     | valores
        new Rebocado(login: "felipe")                 | [login: "felipe"]
        new Rebocado()                                | [login: "   "]
        new Rebocado(nome: "Felipe")                  | [nome: "Felipe"]
        new Rebocado()                                | [nome: "  "]
        new Rebocado(email: "Felipe")                 | [email: "felipe@rebocar.me"]
        new Rebocado()                                | [email: "   "]
        new Rebocado(celular: "992130056")            | [celular: "992130056"]
        new Rebocado()                                | [celular: "    "]
        new Rebocado(endereco: "Rua das Laranjeiras") | [endereco: "Rua das Laranjeiras"]
        new Rebocado()                                | [endereco: "    "]
        new Rebocado(cep: "22270243")                 | [cep: "22270243"]
        new Rebocado()                                | [cep: "    "]
        new Rebocado(login: "felipe", nome: "Felipe", email: "felipe@rebocar.me", celular: "992130056", endereco: "Rua das Laranjeiras", cep: "22270243") | [login: "felipe", nome: "Felipe", email: "felipe@rebocar.me", celular: "992130056", endereco: "Rua das Laranjeiras", cep: "22270243"]
    }
}
