package com.pantaleon.registerdaps;

import io.github.josecarlosbran.JBSqlUtils.Enumerations.DataBase;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.DataBaseUndefind;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.PropertiesDBUndefined;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.ValorUndefined;
import io.github.josecarlosbran.JBSqlUtils.JBSqlUtils;
import io.github.josecarlosbran.LogsJB.LogsJB;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends Application {

    public HelloApplication(){
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
        /*
        LogsModel logs=new LogsModel();
        UserModel usuario=new UserModel();
        SignModel sign=new SignModel();
        logs.crateTable();
        usuario.crateTable();
        sign.crateTable();
        */
    }



    private void setearPropertiesDB(){
        JBSqlUtils.setDataBaseGlobal("registerdaps");
        JBSqlUtils.setPortGlobal("5075");
        JBSqlUtils.setHostGlobal("localhost");
        JBSqlUtils.setUserGlobal("bran");
        JBSqlUtils.setPasswordGlobal("bran");
    }

    private void setearDBType(){
        JBSqlUtils.setDataBaseTypeGlobal(DataBase.MySQL);
    }


}