package com.pantaleon.registerdaps.Models.Autenticacion;

import io.github.josecarlosbran.JBSqlUtils.Column;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.Constraint;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.DataType;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.DataBaseUndefind;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.PropertiesDBUndefined;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.ValorUndefined;
import io.github.josecarlosbran.JBSqlUtils.JBSqlUtils;
import lombok.Getter;
import lombok.Setter;

public class SignModel extends JBSqlUtils {

    /**
     *
     * @throws DataBaseUndefind lanza la exepciones DataBaseUndefind si no encuentra definida el tipo de BD`S a la que se
     * conectara el modelo
     * @throws PropertiesDBUndefined lanza excepcion si en las propiedades del sistema no estan definidas las propiedades
     * de conxeion para conectarse  ala BD's especificada
     * @throws ValorUndefined
     */
    public SignModel() throws DataBaseUndefind, PropertiesDBUndefined, ValorUndefined {
        super();
        this.setTableName("Sign");

    }

    @Getter
    @Setter
    private Column<Integer> Id=new Column<>(DataType.INTEGER, Constraint.AUTO_INCREMENT, Constraint.PRIMARY_KEY);

    @Getter @Setter private Column<String> Key=new Column<>(DataType.VARCHAR);

    @Getter @Setter private Column<Boolean> State=new Column<>(DataType.BOOLEAN);

    @Getter @Setter private Column<String> Subject=new Column<>(DataType.VARCHAR);










}
