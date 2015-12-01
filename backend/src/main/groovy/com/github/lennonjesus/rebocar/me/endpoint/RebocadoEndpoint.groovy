package com.github.lennonjesus.rebocar.me.endpoint

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.github.lennonjesus.rebocar.me.entity.Rebocado
import com.github.lennonjesus.rebocar.me.marshall.JSONMarshaller
import com.github.lennonjesus.rebocar.me.repository.RebocadoRepository
import org.codehaus.jettison.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@Component
@Path("/rebocados")
class RebocadoEndpoint {

    @Autowired
    RebocadoRepository rebocadoRepository

    @Context
    private UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response create(String json) {
        JSONObject request = new JSONObject(json)

        if(rebocadoRepository.findByLogin(request.get("login"))){
            return Response
                    .status(409)
                    .entity(JSONMarshaller.marshallErrorMessage("Ja existe um usuario com o mesmo login"))
                    .build()
        }

        Rebocado rebocado = rebocadoRepository.save(Rebocado.fromJSON(request))

        URI location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .resolveTemplate("id", rebocado.login)
                .build()

        Response.ResponseBuilder response = Response
                .created(location)
                .entity(JSONMarshaller.marshall(rebocado))

        return response.build()
    }
}
