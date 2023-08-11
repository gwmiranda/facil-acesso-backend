package org.unibrasil.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.unibrasil.entity.Usuario;
import org.unibrasil.entity.dto.UsuarioDTO;
import org.unibrasil.service.UsuarioService;

import java.util.List;

@Path("/api/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioApi {

    @Inject
    UsuarioService usuarioService;


    @POST
    public void criarUsuario(UsuarioDTO usuarioDTO) {
        usuarioService.criarUsuario(new Usuario(usuarioDTO));
    }

    @GET
    public List<Usuario> buscarTodos() {
        return usuarioService.buscarTodosUsuarios();
    }

    @GET
    @Path("/{id}")
    public Usuario buscarPorId(@PathParam("id") long id) {
        return usuarioService.buscarPorId(id);
    }

    @PUT
    @Path("{id}")
    public Usuario atualizarUsuario(@PathParam("id") long id, UsuarioDTO usuarioDTO) {
        return usuarioService.atualizarUsuario(id, new Usuario(usuarioDTO));
    }

    @DELETE
    @Path("/{id}")
    public boolean deletarPorId(@PathParam("id") long id) {
        return usuarioService.deletarPorId(id);
    }
}
