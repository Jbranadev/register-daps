package com.pantaleon.registerdaps.Controllers.Registro;

import com.josebran.LogsJB.LogsJB;
import com.pantaleon.registerdaps.Controllers.Interfaces.IsResource;
import com.pantaleon.registerdaps.Models.Registro.RegistroModel;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.Operator;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

public class RegistroController implements IsResource {
    private RegistroModel model;

    @Getter @Setter private Integer Id_Registro;

    @Getter @Setter private Integer Id_Usuario;

    @Getter @Setter private Integer Id_Material;

    @Getter @Setter private Integer Ficha;

    @Getter @Setter private Integer Cantidad_Existente;

    @Getter @Setter private Integer Cantidad_Entregada;

    @Getter @Setter private Timestamp Fecha_Entrega;

    public RegistroController() {
        try{
            this.model = new RegistroModel();
        }catch (Exception e) {
            LogsJB.fatal("Excepción disparada al inicializar el controlador: "+ e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }
    }



    public void get(Integer id){
        try{
            model.where("Id_Registro", Operator.IGUAL_QUE, id).get();
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




}
