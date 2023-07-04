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
import lombok.Getter;
import lombok.Setter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * se implementan los metodos de la interfaz IsResource
 */
public class UsuarioController implements IsResource {
    /**
     * Definiendo los atributos del modelo,
     * * @Getter con esta etiqueta creamos los get por medio de la dependencia lombok
     * @Setter con esta etiqueta creamos los set por medio de la dependencia lombok
     */

    @JsonbProperty("id_usuario")
    @Getter @Setter private Integer Id_Usuario;

    @JsonbProperty("usuario")
    @Getter @Setter private String Usuario;

    @JsonbProperty("passworduser")
    @Getter @Setter private String PasswordUser;

    @JsonbTransient
    @Getter @Setter private String TokenActual;

    @JsonbTransient
    @Getter @Setter private String TokenAnterior;

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
