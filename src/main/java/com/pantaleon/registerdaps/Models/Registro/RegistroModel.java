package com.pantaleon.registerdaps.Models.Registro;

import io.github.josecarlosbran.JBSqlUtils.Column;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.Constraint;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.DataType;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.DataBaseUndefind;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.PropertiesDBUndefined;
import io.github.josecarlosbran.JBSqlUtils.JBSqlUtils;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

public class RegistroModel extends JBSqlUtils {

    public RegistroModel() throws DataBaseUndefind, PropertiesDBUndefined {
        super();
        this.setTableName("Registros");

    }

    @Getter
    @Setter
    private Column <Integer> Id_Registro=new
            Column<>(DataType.INTEGER, Constraint.AUTO_INCREMENT, Constraint.PRIMARY_KEY);

    @Getter
    @Setter
    private Column <Integer> Id_Usuario = new Column<>(DataType.INTEGER, Constraint.NOT_NULL);

    @Getter
    @Setter
    private Column <Integer> Id_Material = new Column<>(DataType.INTEGER, Constraint.NOT_NULL);

    @Getter
    @Setter
    private Column <Integer> Ficha = new Column<>(DataType.INTEGER, Constraint.NOT_NULL);

    @Getter
    @Setter
    private Column <Integer> Cantidad_Existente = new Column<>(DataType.INTEGER, Constraint.NOT_NULL);

    @Getter
    @Setter
    private Column <Integer> Cantidad_Entregada = new Column<>(DataType.INTEGER, Constraint.NOT_NULL);

    @Getter
    @Setter
    private Column <Timestamp> Fecha_Entrega = new Column<>(DataType.TIMESTAMP, "CURRENT_TIMESTAMP", Constraint.NOT_NULL);





}
