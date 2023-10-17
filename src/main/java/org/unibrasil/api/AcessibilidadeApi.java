package org.unibrasil.api;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.ValidationException;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.unibrasil.api.exception.ErrorResponse;
import org.unibrasil.api.exception.ResponseException;
import org.unibrasil.entity.Acessibilidade;
import org.unibrasil.entity.dto.AcessibilidadeDTO;
import org.unibrasil.service.AcessibilidadeService;

@Path("/api/acessibilidade")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AcessibilidadeApi {

    @Inject
    AcessibilidadeService acessibilidadeService;

    @POST
    @Authenticated
    public Response criarAcessibilidade(AcessibilidadeDTO acessibilidadeDTO)  {
        try {
            acessibilidadeService.gravarAcessibilidade(new Acessibilidade(acessibilidadeDTO.getDescricao(), acessibilidadeDTO.getIcon()));

            return Response.status(Response.Status.CREATED)
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
        }
    }

    @GET
    @Authenticated
    public Response buscarTodos() {
        var acessibilidades = acessibilidadeService.buscarTodasAcessibilidades();

        return Response.status(Response.Status.OK)
                .entity(acessibilidades)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("/{id}")
    @Authenticated
    public Response buscarPorId(@PathParam("id") long id) {
        try {
            var acessibilidade = acessibilidadeService.buscarPorId(id);

            return Response.status(Response.Status.OK)
                    .entity(acessibilidade)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
        }
    }

    @PUT
    @Path("/{id}")
    @Authenticated
    public Response atualizar(@PathParam("id") long id, AcessibilidadeDTO acessibilidadeDTO) {
        try {
            var acessibilidade = acessibilidadeService.atualizar(id, new Acessibilidade(acessibilidadeDTO.getDescricao(), acessibilidadeDTO.getIcon()));

            return Response.status(Response.Status.OK)
                    .entity(acessibilidade)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    public Response deletarPorId(@PathParam("id") long id) {
        try {
            acessibilidadeService.deletarPorId(id);

            return Response.status(Response.Status.OK)
                    .entity("Acessiblidade deletada")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
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
