package org.unibrasil.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.ValidationException;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.unibrasil.api.exception.ErrorResponse;
import org.unibrasil.api.exception.ResponseException;
import org.unibrasil.entity.Favorito;
import org.unibrasil.entity.dto.FavoritoDTO;
import org.unibrasil.service.FavoritoService;

@Path("/api/favorito")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FavoritoApi {

    @Inject
    FavoritoService favoritoService;

    @POST
    //@Authenticated
    public Response criarFavorito(FavoritoDTO favoritoDTO)  {
        try {
            favoritoService.gravarFavorito(new Favorito(favoritoDTO));

            return Response.status(Response.Status.CREATED)
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
        }
    }

    @GET
    @Path("/{id}")
    //@Authenticated
    public Response buscarFavoritoPorId(@PathParam("id") long id) {
        try {
            var favorito = favoritoService.buscarPorId(id);

            return Response.status(Response.Status.OK)
                    .entity(favorito)
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
            favoritoService.deletarPorId(id);

            return Response.status(Response.Status.OK)
                    .entity("Favorito deletado")
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
