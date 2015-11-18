package com.github.lennonjesus.rebocar.me.marshall

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.JsonGenerator
import com.github.lennonjesus.rebocar.me.entity.SelfMarshallingEntity
import org.apache.commons.lang3.StringUtils

class JSONMarshaller {

    private static final String ERROR = "error"

    public static <S extends SelfMarshallingEntity> String marshall(String collectionName, Collection<S> collection) throws IOException {
        if(StringUtils.isBlank(collectionName)){
            return null
        }

        StringWriter sw = new StringWriter()
        JsonFactory f = new JsonFactory()
        JsonGenerator g = f.createJsonGenerator(sw)

        g.writeStartObject()
        g.writeArrayFieldStart(collectionName)

        collection.each { SelfMarshallingEntity elemento ->
            elemento.asJson(g)
        }

        g.writeEndArray()
        g.writeEndObject()
        g.close()

        return sw.toString()
    }

    public static marshallErrorMessage(String errorMessage){
        StringWriter sw = new StringWriter()
        JsonFactory f = new JsonFactory()
        JsonGenerator g = f.createJsonGenerator(sw)

        g.writeStartObject()
        g.writeStringField(ERROR, errorMessage)
        g.writeEndObject()

        g.close()

        return sw.toString()
    }
}
