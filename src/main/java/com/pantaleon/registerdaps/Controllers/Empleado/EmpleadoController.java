package com.pantaleon.registerdaps.Controllers.Empleado;

import com.josebran.LogsJB.LogsJB;
import com.pantaleon.registerdaps.Controllers.Interfaces.IsResource;
import com.pantaleon.registerdaps.Models.Empleado.EmpleadoModel;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.Operator;
import jakarta.json.bind.annotation.JsonbProperty;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoController implements IsResource {
    private EmpleadoModel model ;
    private Integer Ficha;

    private String Nombre;
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


    public List<EmpleadoController> obtenerAllEmpleados(){
        try{
            List<EmpleadoController> empleados=new ArrayList<>();
            List<EmpleadoModel> modelos=this.model.getAll();
            while(!model.getTaskIsReady()){
            }
            modelos.forEach(modelo->{
                EmpleadoController temp= new EmpleadoController();
                modelo.llenarControlador(temp, modelo);
                empleados.add(temp);
            });
            return empleados;
        }catch (Exception e) {
            LogsJB.fatal("Excepción disparada al obtener la lista de empleados de la BD's: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return new ArrayList<EmpleadoController>();
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

    @JsonbProperty("Ficha")
    public Integer getFicha() {
        return Ficha;
    }
    @JsonbProperty("Ficha")
    public void setFicha(Integer ficha) {
        Ficha = ficha;
    }
    @JsonbProperty("Nombre")
    public String getNombre() {
        return Nombre;
    }
    @JsonbProperty("Nombre")
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    @JsonbProperty("Puesto")
    public String getPuesto() {
        return Puesto;
    }
    @JsonbProperty("Puesto")
    public void setPuesto(String puesto) {
        Puesto = puesto;
    }
}
