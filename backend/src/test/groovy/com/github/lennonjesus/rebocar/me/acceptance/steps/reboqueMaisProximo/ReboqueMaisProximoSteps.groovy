package com.github.lennonjesus.rebocar.me.acceptance.steps.reboqueMaisProximo

import com.github.lennonjesus.rebocar.me.RebocarMe
import com.github.lennonjesus.rebocar.me.endpoint.CaminhaoEndpoint
import com.github.lennonjesus.rebocar.me.entity.Caminhao
import com.github.lennonjesus.rebocar.me.testhelpers.ResponseParser
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.test.context.ContextConfiguration

import javax.ws.rs.core.MultivaluedHashMap
import javax.ws.rs.core.MultivaluedMap
import javax.ws.rs.core.UriInfo

import static org.junit.Assert.assertEquals
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

@ContextConfiguration(classes = RebocarMe, loader = SpringApplicationContextLoader.class)
class ReboqueMaisProximoSteps {

    @Autowired
    MongoTemplate mongoTemplate

    @Autowired
    CaminhaoEndpoint endpoint

    UriInfo uriInfoMock
    ResponseParser response

    public ReboqueMaisProximoSteps(){
        init()
    }

    private void init() {
        uriInfoMock = mock(UriInfo)
        when(uriInfoMock.queryParameters).thenReturn(new MultivaluedHashMap<String, String>())
    }

    @Given("^que a minha localização atual é \\(([-+]?[0-9]*\\.?[0-9]+),([-+]?[0-9]*\\.?[0-9]+)\\)\$")
    public void que_a_minha_localização_atual_é_(double lat, double lng) throws Throwable {
        MultivaluedMap<String, Double> params = new MultivaluedHashMap<>()
        params.add("lat", lat)
        params.add("lng", lng)
        when(uriInfoMock.queryParameters).thenReturn(params)
    }

    @Given("^há um reboque com a placa \"([^\"]*)\" na localização \\([-+]?([0-9]*\\.[0-9]+|[0-9]+),[-+]?([0-9]*\\.[0-9]+|[0-9]+)\\)\$")
    public void há_um_reboque_com_a_placa_na_localização_(String placa, double lat, double lng) throws Throwable {
        Query query = new Query().addCriteria(Criteria.where("placa").is(placa))
        Caminhao existingCaminhao = mongoTemplate.findOne(query, Caminhao.class)

        if(!existingCaminhao){
            mongoTemplate.save(new Caminhao(placa: placa, posicao: [lat, lng]))
        }
    }

    @When("^eu busco pelo reboque mais próximo\$")
    public void eu_busco_pelo_reboque_mais_próximo() throws Throwable {
        response = new ResponseParser(endpoint.findAll(uriInfoMock))
    }

    @Then("^a busca não retorna nenhum resultado\$")
    public void a_busca_não_retorna_nenhum_resultado() throws Throwable {
        assertEquals(200, response.status)
        assertEquals(0, response.attrs.caminhoes.size())
    }

    @Then("^a busca retorna (\\d+) reboque com a placa \"([^\"]*)\"\$")
    public void a_busca_retorna_reboque_com_a_placa(int totalCaminhoes, String placa) throws Throwable {
        assertEquals(200, response.status)
        assertEquals(totalCaminhoes, response.attrs.caminhoes.size())
        assertEquals(placa, response.attrs.caminhoes[0].placa)
    }

}
