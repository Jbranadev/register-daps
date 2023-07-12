package com.pantaleon.registerdaps.Resources.Registro;

import com.josebran.LogsJB.LogsJB;
import com.pantaleon.registerdaps.Controllers.Registro.RegistroController;
import com.pantaleon.registerdaps.Resources.Autenticacion.Authorize;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Authorize
@Path("/registro")
public class RegistroResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(){
        try{
            RegistroController registro=new RegistroController();
            List<RegistroController> registros =new ArrayList<>();
            registros=registro.obtenerAllRegistros();
            return Response.ok().entity(registros).build();
        }catch (Exception e){
            LogsJB.fatal("Excepción disparada al tratar de obtener los registros: "+": " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("Excepción disparada al tratar de obtener los registros: ").build();
        }
    }


    /**
     * @return retorna una respuesta http con el estado correspondiente
     * @Produces con esta etiqueta se esta enviando dato  tipo JSON
     * @Consumes especificamos el tipo de de formato JSON
     */
    @Path("/guardar")
    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(RegistroController registro) {
        try {
            if (!registro.isFull("Id_Usuario", "Id_Material", "Ficha",
                    "Cantidad_Existente", "Cantidad_Entregada")) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Asegúrese de enviar la solicitud acorde " +
                        "al contenido solicitado por el servidor.").build();
            }
            registro.save();
            return Response.status(Response.Status.CREATED).entity(registro).build();

        } catch (Exception e) {
            LogsJB.fatal("Excepción disparada al insertar el registro " + registro.toString() + ": " + e.toString());
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
    public Response update(RegistroController registro) {
        try {
            if (!registro.isFull("Id_Registro", "Id_Usuario", "Id_Material", "Ficha",
                    "Cantidad_Existente", "Cantidad_Entregada")) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Asegúrese de enviar la solicitud acorde " +
                        "al contenido solicitado por el servidor.").build();
            }
            registro.update();
            return Response.ok().entity(registro).build();

        } catch (Exception e) {
            LogsJB.fatal("Excepción disparada al actualizar el registro " + registro.toString() + ": " + e.toString());
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
    public Response delete(RegistroController registro) {
        try {
            if (!registro.isFull("Id_Registro")) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Asegúrese de enviar la solicitud acorde " +
                        "al contenido solicitado por el servidor.").build();
            }
            registro.delete();
            return Response.ok().entity(registro).build();
        } catch (Exception e) {
            LogsJB.fatal("Excepción disparada al eliminar el registro " + registro.toString() + ": " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return Response.serverError().entity("Sucedio un error al eliminar usuario").build();
        }
    }

}
