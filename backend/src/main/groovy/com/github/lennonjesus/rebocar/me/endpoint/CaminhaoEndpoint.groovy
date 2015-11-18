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
    private final String LATITUDE = "lat"
    private final String LONGITUDE = "lng"
    private final String COLLECTION_NAME = "caminhoes"

    @Autowired
    CaminhaoRepository caminhaoRepository

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@Context UriInfo query){
        Map<String, Double> currentPosition = extractCurrentPosition(query)

        if(!currentPosition || !currentPosition.lat || !currentPosition.lng){
            return Response.status(422)
                    .entity(JSONMarshaller.marshallErrorMessage("Parameters 'lat' and 'lng' are required when executing a search."))
                    .build()
        }

        Integer raio = extractRaio(query)
        List<Caminhao> caminhoes = caminhaoRepository.findAllByPosicaoNear(
                new Point(currentPosition.lat, currentPosition.lng),
                new Distance(raio, Metrics.KILOMETERS)
        )

        Response.ResponseBuilder builder = Response.ok(JSONMarshaller.marshall(COLLECTION_NAME, caminhoes));
        return builder.build();
    }

    private Map<String, Double> extractCurrentPosition(UriInfo query){
        return [lat: query.queryParameters.getFirst(LATITUDE)?.toDouble(), lng: query.queryParameters.getFirst(LONGITUDE)?.toDouble()]
    }

    private Integer extractRaio(UriInfo query){
        if(query.queryParameters."$RAIO"){
            return Integer.valueOf(query.queryParameters.getFirst(RAIO))
        }
        return DEFAULT_RADIUS_IN_KM
    }
}
