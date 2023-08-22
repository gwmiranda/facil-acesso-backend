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
import org.unibrasil.entity.AlterarSenhaRequest;
import org.unibrasil.entity.Usuario;
import org.unibrasil.entity.dto.UsuarioDTO;
import org.unibrasil.service.UsuarioService;

@Path("/api/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioApi {

    @Inject
    UsuarioService usuarioService;

    @POST
    @PermitAll
    public Response criarUsuario(UsuarioDTO usuarioDTO)  {
        try {
            usuarioService.criarUsuario(new Usuario(usuarioDTO));

            return Response.status(Response.Status.CREATED)
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
        }
    }

    @GET
    public Response buscarTodos() {
        var usuarios = usuarioService.buscarTodosUsuarios();

        return Response.status(Response.Status.OK)
                .entity(usuarios)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") long id) {
        try {
            var usuario = usuarioService.buscarPorId(id);

            return Response.status(Response.Status.OK)
                    .entity(usuario)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarUsuario(@PathParam("id") long id, UsuarioDTO usuarioDTO) {
        try {
            var usuario = usuarioService.atualizarUsuario(id, new Usuario(usuarioDTO));

            return Response.status(Response.Status.OK)
                    .entity(usuario)
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
            usuarioService.deletarPorId(id);

            return Response.status(Response.Status.OK)
                    .entity("Usuário deletado")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
        }
    }

    @PUT
    @Path("/senha")
    public void alterarSenha(AlterarSenhaRequest alterarSenha) {
        try {
            usuarioService.alterarSenha(alterarSenha.getId(), alterarSenha.getSenhaAtual(), alterarSenha.getSenhaNova());
        } catch (ValidationException e) {
            throw new ResponseException(e.getMessage(), e);
        }
    }

    @POST
    @Path("/senha/{email}")
    public String recuperarSenha(@PathParam("email") String email) {
        try {
            return usuarioService.recuperarSenha(email);
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
