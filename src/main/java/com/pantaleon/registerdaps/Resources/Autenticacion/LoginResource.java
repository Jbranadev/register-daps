package com.pantaleon.registerdaps.Resources.Autenticacion;

import com.pantaleon.registerdaps.Controllers.Autenticacion.SignController;
import com.pantaleon.registerdaps.Controllers.Autenticacion.UsuarioController;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.ModelNotFound;
import io.github.josecarlosbran.LogsJB.LogsJB;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Path("/Login")
public class LoginResource {

    @Getter
    private static SignController controlador=new SignController();



    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(UsuarioController usuario)
    {
        try{
            if(!usuario.isFull("Usuario", "PasswordUser")){
                return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Asegúrese de enviar la solicitud acorde " +
                        "al contenido solicitado por el servidor.").build();
            }

            if(Objects.isNull(this.controlador.getSecretKey())){
                this.controlador.obtenerKeyfromDB();
            }

            validarFecha();

            try{
                //Verifica la existencia del usuario en BD's
                usuario.firstOrFail();
                //Acá se crea el token
                String jwtToken = Jwts.builder()
                        .setSubject(LoginResource.getControlador().getSubject())
                        .setIssuer("localhost:8080")
                        .setIssuedAt(new Date())
                        .setExpiration(
                                Date.from(
                                        LocalDateTime.now().
                                                plusMinutes(15L).atZone(ZoneId.systemDefault()).
                                                toInstant())).signWith(LoginResource.controlador.getSecretKey(), SignatureAlgorithm.HS512).compact();
                usuario.setTokenAnterior(usuario.getTokenActual());
                usuario.setTokenActual(jwtToken);
                usuario.update();
                return Response.status(Response.Status.OK).entity(jwtToken).build();
            }catch (ModelNotFound e){
                return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales No Validas").build();
            }

        }
        catch(Exception e)
        {
            LogsJB.fatal("Excepción disparada al tratar de autenticar al usuario: "+usuario.getUsuario()+": " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity("Excepción disparada al tratar de autenticar al usuario: "+usuario.getUsuario()).build();
        }
    }

    public void validarFecha(){
        try{
            LocalDateTime creacionKey= LoginResource.controlador.getCreated_at().toLocalDateTime();
            LocalDateTime fecha_actual=LocalDateTime.now();
            LocalDateTime fecha_valides=creacionKey.plusDays(1);
            //LocalDateTime fecha_valides=creacionKey.plusMinutes(1);
            //Validaremos si la fecha actual es mayor a la fecha de valides, generaremos una nueva secretKey
            if(fecha_actual.isAfter(fecha_valides)){
                //Si la fecha actual es mayor, la key ya vencio
                LoginResource.controlador.updateKeyToFalse();
                //Inicializamos el controlador, como un nuevo controlador
                LoginResource.controlador=new SignController();
                //Generaremos la nueva key y se almacenara en BD's
                LoginResource.controlador.obtenerKeyfromDB();
            }
        }catch (Exception e){
            LogsJB.fatal("Excepción disparada al tratar de generar una nueva key: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }
    }



}
