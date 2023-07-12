package com.pantaleon.registerdaps.Resources.Autenticacion.User;

import com.josebran.LogsJB.LogsJB;
import com.pantaleon.registerdaps.Controllers.Autenticacion.UsuarioController;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @Path con  esta etiqueta  creamos  la rutas /Usuarios
 */
@Path("/usuario")
public class UserResource {

    /**
     * @Path con  esta etiqueta  creamos  la ruta /id
     * @GET con esta etiqueta creamos los Get
     * @Produces con esta etiqueta se esta enviando dato  tipo JSON
     * @PathParam hacemos referencia a id para obtener su valor
     */
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Positive @PathParam("id") Integer id) {
        try {

            UsuarioController user = new UsuarioController();
            user.get(id);

            user.setPasswordUser(null);
            user.setTokenActual(null);
            user.setTokenAnterior(null);
            return Response.ok().entity(user).build();

        } catch (Exception e) {
            LogsJB.fatal("Excepción disparada al obtener el modelo " + id + ": " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return Response.serverError().entity("Sucedio un error al obtener el Log").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(){
        try{
            UsuarioController usuario=new UsuarioController();
            List<UsuarioController> usuarios =new ArrayList<>();
            usuarios=usuario.obtenerAllUsers();
            return Response.ok().entity(usuarios).build();
        }catch (Exception e){
            LogsJB.fatal("Excepción disparada al tratar de obtener los Usuarios: "+": " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("Excepción disparada al tratar de obtener los Usuarios: ").build();
        }
    }


    /**
     * @return retorna una respuesta http con el estado correspondiente
     * @Produces con esta etiqueta se esta enviando dato  tipo JSON
     * @Consumes especificamos el tipo de de formato JSON
     */
    @Path("/guardar")
    @PUT()
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(UsuarioController user) {
        try {
            if (!user.isFull("Usuario", "PasswordUser")) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Asegúrese de enviar la solicitud acorde " +
                        "al contenido solicitado por el servidor.").build();
            }
            user.save();
            LogsJB.info("Usuario creado: "+user.toString());
            return Response.status(Response.Status.CREATED).entity(user).build();

        } catch (Exception e) {
            LogsJB.fatal("Excepción disparada al insertar el usuario " + user.toString() + ": " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return Response.serverError().entity("Sucedio un error al crear el usuario").build();
        }
    }
    /**
     * @return retorna una respuesta http con el estado correspondiente
     * @Produces con esta etiqueta se esta enviando dato  tipo JSON
     * @Consumes especificamos el tipo de de formato JSON
     *
     */
    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(UsuarioController user) {
        try {
            if (!user.isFull("id", "Usuario", "PasswordUser")) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Asegúrese de enviar la solicitud acorde " +
                        "al contenido solicitado por el servidor.").build();
            }
            user.update();
            return Response.ok().entity(user).build();

        } catch (Exception e) {
            LogsJB.fatal("Excepción disparada al actualizar el usuario " + user.toString() + ": " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return Response.serverError().entity("Sucedio un error al actualizar usuario").build();
        }
    }

    /**
     * @return retorna una respuesta http con el estado correspondiente
     * @Produces con esta etiqueta se esta enviando dato  tipo JSON
     * @Consumes especificamos el tipo de de formato JSON
     */
    @DELETE()
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(UsuarioController user) {
        try {
            if (!user.isFull("id")) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Asegúrese de enviar la solicitud acorde " +
                        "al contenido solicitado por el servidor.").build();
            }
            user.delete();
            return Response.ok().entity(user).build();
        } catch (Exception e) {
            LogsJB.fatal("Excepción disparada al eliminar el usuario " + user.toString() + ": " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return Response.serverError().entity("Sucedio un error al eliminar usuario").build();
        }
    }


}

