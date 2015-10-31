package com.github.lennonjesus.rebocar.me.marshall

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.JsonGenerator
import com.github.lennonjesus.rebocar.me.entity.SelfMarshallingEntity

class JSONMarshaller {

    public static <S extends SelfMarshallingEntity> String marshall(Collection<S> collection) throws IOException {
        if(!collection){
            return null
        }

        StringWriter sw = new StringWriter()
        JsonFactory f = new JsonFactory()
        JsonGenerator g = f.createJsonGenerator(sw)

        g.writeStartArray()

        collection.each { SelfMarshallingEntity elemento ->
            elemento.asJson(g)
        }

        g.writeEndArray()
        g.close()

        return sw.toString()
    }
}
