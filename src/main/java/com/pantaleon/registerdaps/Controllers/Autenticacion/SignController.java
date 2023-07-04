package com.pantaleon.registerdaps.Controllers.Autenticacion;

import com.josebran.LogsJB.LogsJB;
import com.pantaleon.registerdaps.Models.Autenticacion.SignModel;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.Operator;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.DataBaseUndefind;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.ModelNotFound;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.PropertiesDBUndefined;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.ValorUndefined;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Timestamp;
import java.util.Base64;

public class SignController {

    @Getter
    @Setter
    private Integer Id;

    @Getter
    @Setter
    private String KeySign;

    @Getter
    @Setter
    private Boolean State;

    @Getter
    @Setter
    private String Subject="Jose Carlos Alfredo Bran Aguirre";

    @Getter
    @Setter
    private Timestamp Created_at;

    @Setter
    @Getter
    SecretKey secretKey;

    private SignModel model;


    /**
     * Inicializamos el modelo correspondiente al controlador en el constructor del mismo
     */
    public SignController() {
        try {
            this.model = new SignModel();
        } catch (Exception e) {
            LogsJB.fatal("Excepción disparada al inicializar el controlador: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }

    }


    /**
     * Busca la key activa
     *
     * @throws ModelNotFound Lanza esta excepción de no encontrar el usuario
     */
    public void obtenerKeyfromDB() {
        try {
            model = (SignModel) model.where("State", Operator.IGUAL_QUE, true).firstOrFail();
            model.llenarControlador(this, model);
            this.obtenerSecretKeyOfStringKey(this.getKeySign());
            LogsJB.info("Obtuvo la SecretKey de BD's");
        } catch (ModelNotFound e) {
            try {
                //Si no encuentra el modelo entonces generara la Key y la almacenara en BD's
                LogsJB.info("No existe la firma almacenada en BD's por lo cual procedera a crear una " + "y almacenarla");
                SecretKey clave = Keys.secretKeyFor(SignatureAlgorithm.HS512);
                this.setSecretKey(clave);
                this.setKeySign(Base64.getEncoder().encodeToString(clave.getEncoded()));
                this.setState(true);
                //Llenamos el modelo con la información de BD's
                //this.model.getKey().setValor(this.getKey());
                //this.model.getState().setValor(true);
                model.llenarModelo(this, model);
                //Guardamos el registro en BD's
                model.save();
                while (!model.getTaskIsReady()) {

                }
                //Llenamos el controlador
                //this.setState(this.model.getState().getValor());
                //this.setCreated_at(this.model.getCreated_at().getValor());

                model.llenarControlador(this, model);
            } catch (Exception ex) {
                LogsJB.fatal("Excepción disparada al crear una nueva secretKey y almacenarla en BD's: " + e.toString());
                LogsJB.fatal("Tipo de Excepción : " + e.getClass());
                LogsJB.fatal("Causa de la Excepción : " + e.getCause());
                LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
                LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            }
        } catch (DataBaseUndefind | PropertiesDBUndefined | ValorUndefined e) {
            LogsJB.fatal("Excepción disparada al obtener el usuario de la BD's: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }

    }

    /**
     * Actualizara la clave actual a False, para que ya no sea tomada en cuenta
     */
    public void updateKeyToFalse(){
        model.setModelExist(true);
        this.setState(false);
        model.llenarModelo(this, model);
        model.save();
    }


    /**
     * Retorna la secretKey del controlador
     * @param key Clave secreta en string
     */
    public void obtenerSecretKeyOfStringKey(String key) {
        byte[] decodedKey = Base64.getDecoder().decode(key);
        SecretKey originalKey = new SecretKeySpec(decodedKey, "HmacSHA512");
        this.setSecretKey(originalKey);
    }


}
