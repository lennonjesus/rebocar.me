package com.github.lennonjesus.rebocar.me.repository

import com.github.lennonjesus.rebocar.me.RebocarMe
import com.github.lennonjesus.rebocar.me.entity.Caminhao
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
class CaminhaoRepositorySpec extends Specification {

    @Autowired
    MongoTemplate template

    @Autowired
    CaminhaoRepository caminhaoRepository

    Caminhao maracana
    Caminhao casaDoAlemao
    Caminhao praiaDoLeblon

    def setup() {
        maracana = new Caminhao(placa: "Maracana", posicao: [-22.9121039,-43.2323445])
        casaDoAlemao = new Caminhao(placa: "Casa do Alemao - Jd Primavera", posicao: [-22.6949728,-43.2898208])
        praiaDoLeblon = new Caminhao(placa: "Praia do Leblon", posicao: [-22.9878367,-43.2235188])
    }

    def cleanup(){
        cleanCollection()
    }

    def "Quando ha 3 caminhoes no Brasil e eu estou na Alemanha, e procuro pelo caminhao mais proximo no raio de 5km, findByPosicaoNear nao retorna nenhum resultado"() {
        given:
        template.save(maracana)
        template.save(casaDoAlemao)
        template.save(praiaDoLeblon)

        when:
        List<Caminhao> caminhoes = caminhaoRepository.findAllByPosicaoNear(new Point(52.374601,9.74889), new Distance(5, Metrics.KILOMETERS))

        then:
        !caminhoes
    }

    def "Quando ha 3 caminhoes no Grande Rio e eu estou no Leblon, e procuro pelo caminhao mais proximo no raio de 5km, findByPosicaoNear retorna o caminhao que esta no Leblon"() {
        given:
        template.save(maracana)
        template.save(casaDoAlemao)
        template.save(praiaDoLeblon)

        when:
        List<Caminhao> caminhoes = caminhaoRepository.findAllByPosicaoNear(new Point(-22.9810913,-43.2169033), new Distance(5, Metrics.KILOMETERS))

        then:
        caminhoes
        1 == caminhoes.size()
        "Praia do Leblon" == caminhoes.first().placa
    }

    private void cleanCollection(){
        template.remove(new Query(), Caminhao)
    }
}
