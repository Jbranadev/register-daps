package com.pantaleon.registerdaps.Resources.Material;

import com.josebran.LogsJB.LogsJB;
import com.pantaleon.registerdaps.Controllers.Material.MaterialController;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("material")
public class MaterialResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(){
        try{
            MaterialController material=new MaterialController();
            List<MaterialController>materiales =new ArrayList<>();
            materiales=material.getAllMaterials();
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






}
