package com.pantaleon.registerdaps.Resources.Material;

import com.josebran.LogsJB.LogsJB;
import com.pantaleon.registerdaps.Controllers.Material.MaterialController;
import com.pantaleon.registerdaps.Resources.Autenticacion.Authorize;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("material")
public class MaterialResource {
    @Authorize
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(){
        try{
            MaterialController material=new MaterialController();
            List<MaterialController>materiales =new ArrayList<>();
            materiales=material.obtenerAllMaterials();
            return Response.ok().entity(materiales).build();
        }catch (Exception e){
            LogsJB.fatal("Excepción disparada al tratar de obtener los materiales: "+": " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("Excepción disparada al tratar de obtener los materiales: ").build();
        }
    }
    @Authorize
    @Path("/guardar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(MaterialController material){
        try{
            if (!material.isFull("Descripcion", "Id_Tarjeta_NFC", "Cantidad_Existente")) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Asegúrese de enviar la solicitud acorde " +
                        "al contenido solicitado por el servidor.").build();
            }
            material.save();
            return Response.status(Response.Status.CREATED).entity(material).build();
        }catch (Exception e){
            LogsJB.fatal("Excepción disparada al insertar el material " + material.toString() + ": " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return Response.serverError().entity("Sucedio un error al crear el Material").build();
        }
    }

    @Authorize
    @Path("/actualizar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(MaterialController material){
        try{
            if (!material.isFull("Id_Material","Descripcion", "Id_Tarjeta_NFC", "Cantidad_Existente")) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Asegúrese de enviar la solicitud acorde " +
                        "al contenido solicitado por el servidor.").build();
            }
            material.update();
            return Response.status(Response.Status.OK).entity(material).build();
        }catch (Exception e){
            LogsJB.fatal("Excepción disparada al actualizar el material " + material.toString() + ": " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return Response.serverError().entity("Sucedio un error al actualizar el Material").build();
        }
    }

    @Authorize
    @Path("/eliminar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(MaterialController material){
        try{
            if(!material.isFull("Id_Material")){
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Asegúrese de enviar la solicitud acorde " +
                        "al contenido solicitado por el servidor.").build();
            }
            material.delete();
            return Response.status(Response.Status.OK).entity(material).build();
        }catch (Exception e){
            LogsJB.fatal("Excepción disparada al actualizar el material " + material.toString() + ": " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return Response.serverError().entity("Sucedio un error al eliminar el Material").build();
        }
    }






}
