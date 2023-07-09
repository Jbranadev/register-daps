package com.pantaleon.registerdaps.Controllers.Registro;

import com.josebran.LogsJB.LogsJB;
import com.pantaleon.registerdaps.Controllers.Interfaces.IsResource;
import com.pantaleon.registerdaps.Controllers.Material.MaterialController;
import com.pantaleon.registerdaps.Models.Material.MaterialModel;
import com.pantaleon.registerdaps.Models.Registro.RegistroModel;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.Operator;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RegistroController implements IsResource {
    private RegistroModel model;

    @JsonbProperty("Id_Registro")
    @Getter @Setter private Integer Id_Registro;

    @JsonbProperty("Id_Usuario")
    @Getter @Setter private Integer Id_Usuario;

    @JsonbProperty("Id_Material")
    @Getter @Setter private Integer Id_Material;

    @JsonbProperty("Ficha")
    @Getter @Setter private Integer Ficha;

    @JsonbProperty("Cantidad_Existente")
    @Getter @Setter private Integer Cantidad_Existente;

    @JsonbProperty("Cantidad_Entregada")
    @Getter @Setter private Integer Cantidad_Entregada;

    @JsonbProperty("Fecha_Entrega")
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


    public List<RegistroController> getAllRegistros(){
        try{
            List<RegistroController> registros=new ArrayList<>();
            List<RegistroModel> modelos=this.model.getAll();
            while(!model.getTaskIsReady()){
            }
            modelos.forEach(modelo->{
                RegistroController temp= new RegistroController();
                modelo.llenarControlador(temp, modelo);
                registros.add(temp);
            });
            return registros;
        }catch (Exception e) {
            LogsJB.fatal("Excepción disparada al obtener la lista de Registros de la BD's: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return new ArrayList<RegistroController>();
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
