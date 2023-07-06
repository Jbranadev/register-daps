package com.pantaleon.registerdaps.Models.Empleado;

import io.github.josecarlosbran.JBSqlUtils.Column;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.Constraint;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.DataType;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.DataBaseUndefind;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.PropertiesDBUndefined;
import io.github.josecarlosbran.JBSqlUtils.JBSqlUtils;
import lombok.Getter;
import lombok.Setter;

public class EmpleadoModel extends JBSqlUtils {

    public EmpleadoModel() throws DataBaseUndefind, PropertiesDBUndefined {
        super();
        this.setTableName("Empleado");
        this.getNombre().getDataTypeSQL().setSize("200");
        this.getPuesto().getDataTypeSQL().setSize("200");
    }

    @Getter @Setter
    private Column<Integer> Ficha = new Column<Integer>(
            DataType.INTEGER, Constraint.PRIMARY_KEY, Constraint.AUTO_INCREMENT, Constraint.NOT_NULL
    );

    @Getter @Setter
    private Column<String> Nombre = new Column<String>(DataType.VARCHAR, Constraint.NOT_NULL);

    @Getter @Setter
    private Column<String> Puesto = new Column<String>(DataType.VARCHAR, Constraint.NOT_NULL);




}
