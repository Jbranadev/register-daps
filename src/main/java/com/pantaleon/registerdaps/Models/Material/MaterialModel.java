package com.pantaleon.registerdaps.Models.Material;

import io.github.josecarlosbran.JBSqlUtils.Column;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.Constraint;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.DataType;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.DataBaseUndefind;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.PropertiesDBUndefined;
import io.github.josecarlosbran.JBSqlUtils.JBSqlUtils;
import lombok.Getter;
import lombok.Setter;

public class MaterialModel extends JBSqlUtils {

    public MaterialModel() throws DataBaseUndefind, PropertiesDBUndefined {
        super();
        this.setTableName("Material");
        this.getDescripcion().getDataTypeSQL().setSize("1000");
    }

    @Getter
    @Setter
    private Column<Integer> Id_Material = new Column<Integer>
            (DataType.INTEGER, Constraint.PRIMARY_KEY, Constraint.AUTO_INCREMENT);

    @Getter
    @Setter
    private Column<String> Descripcion = new Column<String>(
            DataType.VARCHAR);

    @Getter
    @Setter
    private Column<Integer> Id_Tarjeta_NFC = new Column<Integer>(DataType.INTEGER,
            Constraint.NOT_NULL);

    @Getter
    @Setter
    private Column<Integer> Cantidad_Existente = new Column<Integer>(DataType.INTEGER,
            "0", Constraint.DEFAULT, Constraint.NOT_NULL);





}
