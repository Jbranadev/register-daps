package com.pantaleon.registerdaps.Controllers.Registro;

import com.josebran.LogsJB.LogsJB;
import com.pantaleon.registerdaps.Controllers.Interfaces.IsResource;
import com.pantaleon.registerdaps.Models.Registro.RegistroModel;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.Operator;
import jakarta.json.bind.annotation.JsonbProperty;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RegistroController implements IsResource {
    private RegistroModel model;

    private Integer Id_Registro;

    private Integer Id_Usuario;

    private Integer Id_Material;

    private Integer Ficha;

    private Integer Cantidad_Existente;

    private Integer Cantidad_Entregada;

    private Timestamp Fecha_Entrega;

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
    public List<RegistroController> obtenerAllRegistros(){
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

    @JsonbProperty("Id_Registro")
    public Integer getId_Registro() {
        return Id_Registro;
    }
    @JsonbProperty("Id_Registro")
    public void setId_Registro(Integer id_Registro) {
        Id_Registro = id_Registro;
    }
    @JsonbProperty("Id_Usuario")
    public Integer getId_Usuario() {
        return Id_Usuario;
    }
    @JsonbProperty("Id_Usuario")
    public void setId_Usuario(Integer id_Usuario) {
        Id_Usuario = id_Usuario;
    }
    @JsonbProperty("Id_Material")
    public Integer getId_Material() {
        return Id_Material;
    }
    @JsonbProperty("Id_Material")
    public void setId_Material(Integer id_Material) {
        Id_Material = id_Material;
    }
    @JsonbProperty("Ficha")
    public Integer getFicha() {
        return Ficha;
    }
    @JsonbProperty("Ficha")
    public void setFicha(Integer ficha) {
        Ficha = ficha;
    }
    @JsonbProperty("Cantidad_Existente")
    public Integer getCantidad_Existente() {
        return Cantidad_Existente;
    }
    @JsonbProperty("Cantidad_Existente")
    public void setCantidad_Existente(Integer cantidad_Existente) {
        Cantidad_Existente = cantidad_Existente;
    }
    @JsonbProperty("Cantidad_Entregada")
    public Integer getCantidad_Entregada() {
        return Cantidad_Entregada;
    }
    @JsonbProperty("Cantidad_Entregada")
    public void setCantidad_Entregada(Integer cantidad_Entregada) {
        Cantidad_Entregada = cantidad_Entregada;
    }
    @JsonbProperty("Fecha_Entrega")
    public Timestamp getFecha_Entrega() {
        return Fecha_Entrega;
    }
    @JsonbProperty("Fecha_Entrega")
    public void setFecha_Entrega(Timestamp fecha_Entrega) {
        Fecha_Entrega = fecha_Entrega;
    }
}
