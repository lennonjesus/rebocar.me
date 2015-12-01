package com.github.lennonjesus.rebocar.me.repository

import com.github.lennonjesus.rebocar.me.RebocarMe
import com.github.lennonjesus.rebocar.me.entity.Caminhao
import com.github.lennonjesus.rebocar.me.entity.Rebocado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.data.geo.Distance
import org.springframework.data.geo.Metrics
import org.springframework.data.geo.Point
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = RebocarMe, loader = SpringApplicationContextLoader.class)
class RebocadoRepositorySpec extends Specification {

    @Autowired
    MongoTemplate template

    @Autowired
    RebocadoRepository rebocadoRepository

    def setup(){
        cleanCollection()
    }

    def cleanup(){
        cleanCollection()
    }

    def "Quando passo uma instancia de rebocado, ela eh salva"() {
        given:
        Rebocado rebocado = new Rebocado(
                nome: "Felipe",
                endereco: "Rua das Laranjeiras",
                cep: "20270243",
                email: "felipe@rebocar.me",
                celular: "992130056"
        )

        when:
        Rebocado novo = rebocadoRepository.save(rebocado)
        List<Rebocado> savedInstances = template.find(new Query(), Rebocado)

        then:
        null != novo
        1 == savedInstances.size()
        novo.nome == savedInstances[0].nome
        novo.endereco == savedInstances[0].endereco
        novo.email == savedInstances[0].email
        novo.cep == savedInstances[0].cep
        novo.celular == savedInstances[0].celular
    }

    def "findByLogin retorna a instancia pre-existente com o mesmo login"() {
        given:
        template.save(new Rebocado(
                login: "felipecao",
                nome: "Felipe",
                endereco: "Rua das Laranjeiras",
                cep: "20270243",
                email: "felipe@rebocar.me",
                celular: "992130056"
        ))

        when:
        Rebocado existente = rebocadoRepository.findByLogin("felipecao")

        then:
        existente == new Rebocado(login: "felipecao", nome: "Felipe", endereco: "Rua das Laranjeiras", cep: "20270243", email: "felipe@rebocar.me", celular: "992130056")
    }

    private void cleanCollection(){
        template.remove(new Query(), Rebocado)
    }
}
