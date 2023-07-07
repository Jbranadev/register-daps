package com.pantaleon.registerdaps.Controllers.Empleado;

import com.josebran.LogsJB.LogsJB;
import com.pantaleon.registerdaps.Controllers.Interfaces.IsResource;
import com.pantaleon.registerdaps.Models.Empleado.EmpleadoModel;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.Operator;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Getter;
import lombok.Setter;

public class EmpleadoController implements IsResource {

    private EmpleadoModel model ;
    @JsonbProperty("Ficha")
    @Getter @Setter
    private Integer Ficha;

    @JsonbProperty("Nombre")
    @Getter @Setter
    private String Nombre;

    @JsonbProperty("Puesto")
    @Getter @Setter
    private String Puesto;




    public EmpleadoController()  {
        try{
            this.model = new EmpleadoModel();
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
            model.where("Ficha", Operator.IGUAL_QUE, id).get();
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
