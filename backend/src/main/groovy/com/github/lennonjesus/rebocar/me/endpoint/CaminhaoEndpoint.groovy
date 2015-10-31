package com.github.lennonjesus.rebocar.me.endpoint

import com.github.lennonjesus.rebocar.me.entity.Caminhao
import com.github.lennonjesus.rebocar.me.marshall.JSONMarshaller
import com.github.lennonjesus.rebocar.me.repository.CaminhaoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.geo.Distance
import org.springframework.data.geo.Metrics
import org.springframework.data.geo.Point
import org.springframework.stereotype.Component

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@Component
@Path("/caminhoes")
class CaminhaoEndpoint {

    private final Integer DEFAULT_RADIUS_IN_KM = 5
    private final String RAIO = "raio"

    @Autowired
    CaminhaoRepository caminhaoRepository

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@Context UriInfo query){

        Integer raio = DEFAULT_RADIUS_IN_KM

        if(query.queryParameters."$RAIO"){
            raio = Integer.valueOf(query.queryParameters.getFirst(RAIO))
        }

        List<Caminhao> caminhoes = caminhaoRepository.findAllByPosicaoNear(new Point(-22.9810913,-43.2169033), new Distance(raio, Metrics.KILOMETERS))

        Response.ResponseBuilder builder = Response.ok(JSONMarshaller.marshall(caminhoes));
        return builder.build();
    }
}
