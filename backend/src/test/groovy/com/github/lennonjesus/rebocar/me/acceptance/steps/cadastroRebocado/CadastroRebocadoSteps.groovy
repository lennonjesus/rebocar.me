package com.github.lennonjesus.rebocar.me.acceptance.steps.cadastroRebocado

import com.github.lennonjesus.rebocar.me.acceptance.steps.AbstractAcceptanceStep
import com.github.lennonjesus.rebocar.me.endpoint.RebocadoEndpoint
import com.github.lennonjesus.rebocar.me.entity.Rebocado
import com.github.lennonjesus.rebocar.me.testhelpers.ResponseParser
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.codehaus.jettison.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

import static org.junit.Assert.assertEquals

class CadastroRebocadoSteps extends AbstractAcceptanceStep {

    @Autowired
    RebocadoEndpoint endpoint

    JSONObject rebocado

    CadastroRebocadoSteps() {
        this.rebocado = new JSONObject()
    }

    @Given('^que o meu \"([^\"]*)\" eh \"([^\"]*)\"$')
    public void que_o_meu_eh(String atributo, String valor) throws Throwable {
        rebocado.put(atributo, valor)
    }

    @Given('^meu \"([^\"]*)\" eh \"([^\"]*)\"$')
    public void meu_eh(String atributo, String valor) throws Throwable {
        que_o_meu_eh(atributo, valor)
    }

    @Given('^ja existe um usuario com o login \"([^\"]*)\"$')
    public void ja_existe_um_usuario_com_o_login(String loginJaExistente) throws Throwable {
        mongoTemplate.insert(new Rebocado(login: loginJaExistente, nome: "outro", email: "outro@rebocar.me", celular: "992130056", endereco: "Rua das Laranjeiras", cep: "22270243"))
    }

    @When('^eu executo o cadastro$')
    public void eu_executo_o_cadastro() throws Throwable {
        response = new ResponseParser(endpoint.create(rebocado))
    }

    @Then('^o cadastro eh bem sucedido$')
    public void o_cadastro_eh_bem_sucedido() throws Throwable {
        Query query = new Query().addCriteria(Criteria.where("login").is(rebocado.get("login")))

        assertEquals(201, response.status)
        assertEquals(1, mongoTemplate.find(query, Rebocado.class).size())
    }

    @Then('^o cadastro emite um erro$')
    public void o_cadastro_emite_um_erro() throws Throwable {
        assertEquals(409, response.status)
    }
}
