package com.pantaleon.registerdaps.Controllers.Autenticacion;


import com.josebran.LogsJB.LogsJB;
import com.pantaleon.registerdaps.Controllers.Interfaces.IsResource;
import com.pantaleon.registerdaps.Models.Autenticacion.UserModel;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.Operator;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.DataBaseUndefind;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.ModelNotFound;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.PropertiesDBUndefined;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.ValorUndefined;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.ToString;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * se implementan los metodos de la interfaz IsResource
 */
@ToString
public class UsuarioController implements IsResource {

    private Integer Id_Usuario;


    private String Usuario;


    private String PasswordUser;


    private String Rol;


     private String TokenActual;

    @JsonbTransient
    private String TokenAnterior;

    private UserModel model;

    public UsuarioController() {
        try{
            this.model = new UserModel();
        }catch (Exception e) {
            LogsJB.fatal("Excepción disparada al inicializar el controlador: "+ e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }
    }


    @JsonbProperty("Id_Usuario")
    public Integer getId_Usuario() {
        return Id_Usuario;
    }
    @JsonbProperty("Id_Usuario")
    public void setId_Usuario(Integer id_Usuario) {
        Id_Usuario = id_Usuario;
    }
    @JsonbProperty("Usuario")
    public String getUsuario() {
        return Usuario;
    }
    @JsonbProperty("Usuario")
    public void setUsuario(String usuario) {
        Usuario = usuario;
    }
    @JsonbProperty("Password")
    public String getPasswordUser() {
        return PasswordUser;
    }
    @JsonbProperty("Password")
    public void setPasswordUser(String passwordUser) {
        PasswordUser = passwordUser;
    }
    @JsonbProperty("Rol")
    public String getRol() {
        return Rol;
    }
    @JsonbProperty("Rol")
    public void setRol(String rol) {
        Rol = rol;
    }
    @JsonbProperty("token")
    public String getTokenActual() {
        return TokenActual;
    }
    @JsonbProperty("token")
    public void setTokenActual(String tokenActual) {
        TokenActual = tokenActual;
    }

    public String getTokenAnterior() {
        return TokenAnterior;
    }

    public void setTokenAnterior(String tokenAnterior) {
        TokenAnterior = tokenAnterior;
    }

    /**
     * Busca el usuario correspondiente al nombre de usuario y contraseña proporcionados
     * @throws ModelNotFound Lanza esta excepción de no encontrar el usuario
     */
    public void firstOrFail() throws  ModelNotFound {
        try{
            model= (UserModel) model.where("Usuario", Operator.IGUAL_QUE, this.getUsuario())
                    .and("PasswordUser", Operator.IGUAL_QUE, this.getPasswordUser())
                    .firstOrFail();

            model.llenarControlador(this, model);
        }catch (DataBaseUndefind | PropertiesDBUndefined | ValorUndefined e){
            LogsJB.fatal("Excepción disparada al obtener el usuario de la BD's: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }

    }


    public void get(Integer id){
        try{
            model.where("Id_Usuario", Operator.IGUAL_QUE, id).get();
            while(!model.getTaskIsReady()){

            }

            model.llenarControlador(this, model);

        }catch (Exception e) {
            LogsJB.fatal("Excepción disparada al obtener el modelo de la BD's: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }
    }


    public List<UsuarioController> obtenerAllUsers(){
        try{
            List<UsuarioController> usuarios=new ArrayList<>();
            List<UserModel> modelos=this.model.getAll();
            while(!model.getTaskIsReady()){
            }
            modelos.forEach(modelo->{
                UsuarioController temp= new UsuarioController();
                modelo.llenarControlador(temp, modelo);
                usuarios.add(temp);
            });
            return usuarios;
        }catch (Exception e) {
            LogsJB.fatal("Excepción disparada al obtener la lista de Usuarios de la BD's: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return new ArrayList<UsuarioController>();
        }
    }

    public void save(){
        model.llenarModelo(this, model);
        model.save();
    }

    public void update(){
        model.setModelExist(true);
        model.llenarModelo(this, model);
        model.save();



    }

    public void delete(){
        model.llenarModelo(this, model);
        model.delete();
    }

    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }



}
