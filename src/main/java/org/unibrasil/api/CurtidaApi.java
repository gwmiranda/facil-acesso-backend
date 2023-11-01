package org.unibrasil.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.ValidationException;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.unibrasil.api.exception.ErrorResponse;
import org.unibrasil.api.exception.ResponseException;
import org.unibrasil.entity.Curtida;
import org.unibrasil.entity.dto.CurtidaDTO;
import org.unibrasil.service.CurtidaService;

@Path("/api/curtida")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CurtidaApi {

    @Inject
    CurtidaService curtidaService;

    @POST
    //@Authenticated
    public Response curtir(CurtidaDTO curtidaDTO) {
        if (curtidaDTO.isCurtido()) {
            try {
                curtidaService.gravarCurtida(new Curtida(curtidaDTO));

                return Response.status(Response.Status.OK)
                        .type(MediaType.APPLICATION_JSON)
                        .build();

            } catch (ValidationException e) {
                throw new ResponseException(e.getMessage(), e);
            }
        } else {
            try {
                curtidaService.deletarPorId(new Curtida(curtidaDTO));

                return Response.status(Response.Status.OK)
                        .entity("Curtida deletado")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } catch (ValidationException e) {
                throw new ResponseException(e.getMessage(), e);
            }
        }
    }

    @ServerExceptionMapper
    public Response mapperException(ResponseException ex) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponse(ex.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
