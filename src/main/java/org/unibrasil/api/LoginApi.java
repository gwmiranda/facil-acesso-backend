package org.unibrasil.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.xml.bind.ValidationException;
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
    public Auth realizarLogin(UsuarioDTO usuarioDTO) throws ValidationException {
        var usuario = usuarioService.realizarLogin(usuarioDTO.getLogin(), usuarioDTO.getSenha());

        if (usuario.isEmpty()) {
            throw new ValidationException("Usuário ou senha invalido");
        }

        var response = new Auth();
        response.setNomeUsuario(usuario.get().getLogin());
        response.setToken(tokenService.generate(usuario.get().getLogin()));

        return response;
    }
}
