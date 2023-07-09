package com.pantaleon.registerdaps.Resources.Empleado;

import com.josebran.LogsJB.LogsJB;
import com.pantaleon.registerdaps.Controllers.Empleado.EmpleadoController;
import com.pantaleon.registerdaps.Controllers.Material.MaterialController;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/empleado")
public class EmpleadoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(){
        try{
            EmpleadoController empleado=new EmpleadoController();
            List<EmpleadoController> empleados =new ArrayList<>();
            empleados=empleado.getAllEmpleados();
            return Response.ok().entity(empleados).build();
        }catch (Exception e){
            LogsJB.fatal("Excepción disparada al tratar de obtener los empleados: "+": " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("Excepción disparada al tratar de obtener los empleados: ").build();
        }
    }
}
