package org.unibrasil.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.ValidationException;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.unibrasil.api.exception.ErrorResponse;
import org.unibrasil.api.exception.ResponseException;
import org.unibrasil.entity.TipoEstabelecimento;
import org.unibrasil.entity.dto.TipoEstabelecimentoDTO;
import org.unibrasil.service.TipoEstabelecimentoService;

@Path("/api/estabelecimento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoEstabelecimentoApi {

    @Inject
    TipoEstabelecimentoService tipoEstabelecimentoService;

    @POST
    //@Authenticated
    public Response criarTipoEstabelecimento(TipoEstabelecimentoDTO dto)  {
        try {
            tipoEstabelecimentoService.gravarTipoEstabelecimento(new TipoEstabelecimento(dto.getDescricao(), dto.getIcon()));

            return Response.status(Response.Status.CREATED)
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
        }
    }

    @GET
    //@Authenticated
    public Response buscarTodos() {
        var tipoEstabelecimentos = tipoEstabelecimentoService.buscarTodosTiposEstabelecimentos();

        return Response.status(Response.Status.OK)
                .entity(tipoEstabelecimentos)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("/{id}")
    //@Authenticated
    public Response buscarPorId(@PathParam("id") long id) {
        try {
            var tipoEstabelecimento = tipoEstabelecimentoService.buscarPorId(id);

            return Response.status(Response.Status.OK)
                    .entity(tipoEstabelecimento)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
        }
    }

    @PUT
    @Path("/{id}")
    //@Authenticated
    public Response atualizar(@PathParam("id") long id, TipoEstabelecimentoDTO tipoEstabelecimentoDTO) {
        try {
            var tipoEstabelecimento = tipoEstabelecimentoService.atualizar(id, new TipoEstabelecimento(tipoEstabelecimentoDTO.getDescricao(), tipoEstabelecimentoDTO.getIcon()));

            return Response.status(Response.Status.OK)
                    .entity(tipoEstabelecimento)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
        }
    }

    @DELETE
    @Path("/{id}")
    //@Authenticated
    public Response deletarPorId(@PathParam("id") long id) {
        try {
            tipoEstabelecimentoService.deletarPorId(id);

            return Response.status(Response.Status.OK)
                    .entity("Tipo estabelecimento deletado")
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
