package com.pantaleon.registerdaps.Controllers.Material;

import com.josebran.LogsJB.LogsJB;
import com.pantaleon.registerdaps.Controllers.Interfaces.IsResource;
import com.pantaleon.registerdaps.Models.Material.MaterialModel;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.Operator;
import jakarta.json.bind.annotation.JsonbProperty;

import java.util.ArrayList;
import java.util.List;

public class MaterialController implements IsResource {

    private MaterialModel model;
    private Integer Id_Material;
    private String Descripcion;
    private Integer Id_Tarjeta_NFC;
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
    public List<MaterialController> obtenerAllMaterials(){
        try{
            List<MaterialController> materiales=new ArrayList<>();
            List<MaterialModel> modelos=this.model.getAll();
            while(!model.getTaskIsReady()){
            }
            modelos.forEach(modelo->{
                MaterialController temp= new MaterialController();
                modelo.llenarControlador(temp, modelo);
                materiales.add(temp);
            });
            return materiales;
        }catch (Exception e) {
            LogsJB.fatal("Excepción disparada al obtener la lista de materiales de la BD's: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
            return new ArrayList<MaterialController>();
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

    @JsonbProperty("Id_Material")
    public Integer getId_Material() {
        return Id_Material;
    }
    @JsonbProperty("Id_Material")
    public void setId_Material(Integer id_Material) {
        Id_Material = id_Material;
    }
    @JsonbProperty("Descripcion")
    public String getDescripcion() {
        return Descripcion;
    }
    @JsonbProperty("Descripcion")
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    @JsonbProperty("Id_Tarjeta_NFC")
    public Integer getId_Tarjeta_NFC() {
        return Id_Tarjeta_NFC;
    }
    @JsonbProperty("Id_Tarjeta_NFC")
    public void setId_Tarjeta_NFC(Integer id_Tarjeta_NFC) {
        Id_Tarjeta_NFC = id_Tarjeta_NFC;
    }
    @JsonbProperty("Cantidad_Existente")
    public Integer getCantidad_Existente() {
        return Cantidad_Existente;
    }
    @JsonbProperty("Cantidad_Existente")
    public void setCantidad_Existente(Integer cantidad_Existente) {
        Cantidad_Existente = cantidad_Existente;
    }
}
