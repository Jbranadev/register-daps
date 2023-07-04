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

/**
 * clase UserModel hereda de la clase JbSqlUtils los metodos
 */
public class UserModel extends JBSqlUtils{

    /**
     *
     * @throws DataBaseUndefind se realiza las exepciones DataBaseUndefind si no encuentra definida el tipo de BD`S a la que se
     * conectara el modelo
     * @throws PropertiesDBUndefined Muestra la excepcion si en las propiedades del sistema no estan definidas las propiedades
     * de conxeion para conectarse  ala BD's especificada
     * @throws ValorUndefined
     */

    public UserModel() throws DataBaseUndefind, PropertiesDBUndefined, ValorUndefined {
        super();
        this.setTableName("Usuarios");
    }
    /**
     * Definiendo los atributos del modelo, uno por cada columna en la tabla correspondiente al modelo
     * @Getter con esta etiqueta creamos los get por medio de la dependencia lombok
     * @Setter con esta etiqueta creamos los set por medio de la dependencia lombok
     */

    @Getter @Setter
    private Column<Integer> Id_Usuario=new Column<>(DataType.INTEGER, Constraint.AUTO_INCREMENT, Constraint.PRIMARY_KEY);

    @Getter @Setter private Column<String> Usuario=new Column<>(DataType.VARCHAR);

    @Getter @Setter private Column<String> PasswordUser=new Column<>(DataType.VARCHAR);

    @Getter @Setter private Column<String> TokenActual=new Column<>(DataType.VARCHAR);

    @Getter @Setter private Column<String> TokenAnterior=new Column<>(DataType.VARCHAR);

}
