package com.github.lennonjesus.rebocar.me.entity

import com.fasterxml.jackson.core.JsonGenerator
import groovy.transform.EqualsAndHashCode
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.builder.ToStringBuilder
import org.codehaus.jettison.json.JSONObject
import org.hibernate.validator.constraints.NotBlank
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
@EqualsAndHashCode(excludes = ["id"])
class Rebocado extends Usuario implements SelfMarshallingEntity {

    @Id
    String id

    @NotBlank
    String endereco

    @NotBlank
    String cep

    @Override
    void asJson(JsonGenerator g) {
        g.writeStartObject()

        write("login", login, g)
        write("nome", nome, g)
        write("email", email, g)
        write("celular", celular, g)
        write("endereco", endereco, g)
        write("cep", cep, g)

        g.writeEndObject()
    }

    private void write(String atributo, String valor, JsonGenerator g){
        if(StringUtils.isNotBlank(this."$atributo")){
            g.writeStringField(atributo, valor)
        }
    }

    public static Rebocado fromJSON(JSONObject json){
        if(null == json) {
            return null
        }

        Rebocado r = new Rebocado()

        fillInInstanceWithJSONValues(r, json)

        return r
    }

    private static void fillInInstanceWithJSONValues(Rebocado r, JSONObject json){
        Iterator keys = json.keys()
        String nextKey

        while (keys.hasNext()){
            nextKey = keys.next()
            if(StringUtils.isNotBlank(json.get(nextKey))){
                r."$nextKey" = json.get(nextKey)
            }
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("endereco", endereco)
                .append("cep", cep)
                .toString();
    }
}
