package com.pantaleon.registerdaps.Resources.Autenticacion;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.util.Objects;

@Provider
@Authorize
@Priority(Priorities.AUTHENTICATION)
public class AuthorizeFilter implements ContainerRequestFilter {


    @Override
    public void filter(ContainerRequestContext requestContext){
        if(Objects.isNull(LoginResource.getControlador().getSecretKey())){
            LoginResource.getControlador().obtenerKeyfromDB();
        }


        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        try {
            String token = authorizationHeader.substring("Bearer".length()).trim();
            Claims clavesJwt= Jwts.parserBuilder()
                    .setSigningKey(LoginResource.getControlador().getSecretKey())
                    .build()
                    .parseClaimsJws(token).getBody();

            /*Jwts.parserBuilder()
                    .setSigningKey(Login.controlador.getSecretKey())
                    .build()
                    .parseClaimsJws(token);*/

            if(!clavesJwt.getSubject().equals(LoginResource.getControlador().getSubject())){
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("El token proporcionado No es un token valido").build());
            }


        } catch (ExpiredJwtException e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("El token proporcionado a vencido").build());
        }catch (SignatureException e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("La firma del token proporcionado no corresponde" +
                    "por favor, vuelva a autenticarse").build());
        }catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }





}
