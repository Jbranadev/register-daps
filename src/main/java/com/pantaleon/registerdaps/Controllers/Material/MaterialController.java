package com.pantaleon.registerdaps.Controllers.Material;

import com.josebran.LogsJB.LogsJB;
import com.pantaleon.registerdaps.Controllers.Interfaces.IsResource;
import com.pantaleon.registerdaps.Models.Material.MaterialModel;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.Operator;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Getter;
import lombok.Setter;

public class MaterialController implements IsResource {

    private MaterialModel model;

    @JsonbProperty("Id_Material")
    @Getter
    @Setter
    private Integer Id_Material;

    @JsonbProperty("Descripcion")
    @Getter
    @Setter
    private String Descripcion;

    @JsonbProperty("Id_Tarjeta_NFC")
    @Getter
    @Setter
    private Integer Id_Tarjeta_NFC;

    @JsonbProperty("Cantidad_Existente")
    @Getter
    @Setter
    private Integer Cantidad_Existente;

    public MaterialController() {
        try{
            this.model = new MaterialModel();
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
            model.where("Id_Material", Operator.IGUAL_QUE, id).get();
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
