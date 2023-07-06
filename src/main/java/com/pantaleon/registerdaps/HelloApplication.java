package com.pantaleon.registerdaps;

import com.josebran.LogsJB.LogsJB;
import com.josebran.LogsJB.Numeracion.NivelLog;
import com.pantaleon.registerdaps.Models.Autenticacion.SignModel;
import com.pantaleon.registerdaps.Models.Autenticacion.UserModel;
import com.pantaleon.registerdaps.Models.Empleado.EmpleadoModel;
import com.pantaleon.registerdaps.Models.Material.MaterialModel;
import com.pantaleon.registerdaps.Models.Registro.RegistroModel;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.DataBase;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.DataBaseUndefind;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.PropertiesDBUndefined;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.ValorUndefined;
import io.github.josecarlosbran.JBSqlUtils.JBSqlUtils;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends Application {

    public HelloApplication(){
        LogsJB.setGradeLog(NivelLog.DEBUG);
        Boolean tables_created = true;
        setearDBType();
        setearPropertiesDB();
        try{
            LogsJB.info("Verificará en BD's que las tablas correspondientes a los modelos, existan ");
            createTables();
        } catch (DataBaseUndefind e) {
            setearDBType();
            tables_created=false;
        } catch (PropertiesDBUndefined e) {
            setearPropertiesDB();
            tables_created=false;
        } catch (ValorUndefined e) {

        }finally {
            if(!tables_created){
                try{
                    LogsJB.info("Volvera a intentar verificar en BD's que las tablas correspondientes a los modelos, existan ");
                    createTables();
                } catch (Exception e){
                    LogsJB.fatal("Excepción disparada al tratar de verificar la existencia de las tablas en BD's: "+ e.toString());
                    LogsJB.fatal("Tipo de Excepción : " + e.getClass());
                    LogsJB.fatal("Causa de la Excepción : " + e.getCause());
                    LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
                    LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
                }
            }
        }

    }

    private void createTables() throws DataBaseUndefind, PropertiesDBUndefined, ValorUndefined {
        /*UserModel usuario=new UserModel();
        SignModel sign=new SignModel();
        RegistroModel registro=new RegistroModel();*/
        MaterialModel material=new MaterialModel();
        EmpleadoModel empleado = new EmpleadoModel();

        /*usuario.crateTable();
        sign.crateTable();
        registro.crateTable();

*/
        material.crateTable();
        empleado.crateTable();


    }



    private void setearPropertiesDB(){
        JBSqlUtils.setDataBaseGlobal("registerdaps");
        JBSqlUtils.setPortGlobal("5076");
        JBSqlUtils.setHostGlobal("127.0.0.1");
        JBSqlUtils.setUserGlobal("Bran");
        JBSqlUtils.setPasswordGlobal("Bran");
    }

    private void setearDBType(){
        JBSqlUtils.setDataBaseTypeGlobal(DataBase.MySQL);
    }


}