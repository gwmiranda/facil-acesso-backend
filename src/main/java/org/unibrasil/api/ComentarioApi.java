package org.unibrasil.api;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.ValidationException;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.unibrasil.api.exception.ErrorResponse;
import org.unibrasil.api.exception.ResponseException;
import org.unibrasil.entity.Acessibilidade;
import org.unibrasil.entity.Comentario;
import org.unibrasil.entity.dto.AcessibilidadeDTO;
import org.unibrasil.entity.dto.ComentarioDTO;
import org.unibrasil.service.ComentarioService;

@Path("/api/comentario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComentarioApi {

    @Inject
    ComentarioService comentarioService;

    @POST
    @PermitAll
    public Response criarComentario(ComentarioDTO comentarioDTO)  {
        try {
            comentarioService.gravarComentario(new Comentario(comentarioDTO));

            return Response.status(Response.Status.CREATED)
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") long id, ComentarioDTO acessibilidadeDTO) {
        try {
            var comentario = comentarioService.atualizar(id, new Comentario(acessibilidadeDTO));
            var comentarioAlterado = comentarioService.buscarPorId(comentario.getId());

            return Response.status(Response.Status.OK)
                    .entity(comentarioAlterado)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
        }
    }

    @GET
    public Response buscarTodos() {
        var comentarios = comentarioService.buscarTodosComentarios();

        return Response.status(Response.Status.OK)
                .entity(comentarios)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") long id) {
        try {
            var comentario = comentarioService.buscarPorId(id);

            return Response.status(Response.Status.OK)
                    .entity(comentario)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarPorId(@PathParam("id") long id) {
        try {
            comentarioService.deletarPorId(id);

            return Response.status(Response.Status.OK)
                    .entity("Comentário deletado")
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