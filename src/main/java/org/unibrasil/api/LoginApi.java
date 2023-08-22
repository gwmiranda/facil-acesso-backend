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
import org.unibrasil.entity.Auth;
import org.unibrasil.entity.dto.UsuarioDTO;
import org.unibrasil.service.TokenService;
import org.unibrasil.service.UsuarioService;

@Path("/api/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginApi {

    @Inject
    UsuarioService usuarioService;

    @Inject
    TokenService tokenService;

    @POST
    public Response realizarLogin(UsuarioDTO usuarioDTO) {
        try {
            var usuario = usuarioService.realizarLogin(usuarioDTO.getLogin(), usuarioDTO.getSenha());

            var auth = new Auth();
            auth.setNomeUsuario(usuario.getLogin());
            auth.setToken(tokenService.generate(usuario.getLogin()));

            return Response
                    .status(Response.Status.OK)
                    .entity(auth)
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
