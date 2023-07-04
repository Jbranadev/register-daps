package com.pantaleon.registerdaps.Controllers.Interfaces;


import com.josebran.LogsJB.LogsJB;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public interface IsResource {

    /**
     * método que permite validar si los campos envíados como parametros, estan llenos 
     * @param Campos Se definen los campos que se desea validar que tengan contenido
     * @return True si todos los campos que se envían como parametro, tienen información
     *         False, si alguno de los campos indicados como parametro, vienen vacíos.
     */
    default Boolean isFull(String ... Campos){
        int campos_con_informacion=0;
        try{
            List<String> campos= new ArrayList<>(Arrays.asList(Campos));
            for (String campo : campos){
                List<Method> controladorMethods = new ArrayList<>(Arrays.asList(this.getClass().getMethods()));
                for(Method controladorMethod : controladorMethods){
                    String controllerName=controladorMethod.getName();
                    String claseMethod=controladorMethod.getDeclaringClass().getSimpleName();
                    LogsJB.debug("Nombre del método del objeto: "+controllerName+" Clase a la que pertenece: "+claseMethod);
                    //Si la clase donde se declaro el método pertenece a la clase Object
                    if(claseMethod.equalsIgnoreCase("Object")){
                        continue;
                    }

                    //Si el método no es un método get o set salta a la siguiente iteración
                    if(!(StringUtils.startsWithIgnoreCase(controllerName, "get"))){
                        continue;
                    }

                    //Verificara que el metodo que se obtendra corresponda al campo que se evaluara
                    controllerName=StringUtils.removeStartIgnoreCase(controllerName, "get");
                    if(!campo.equalsIgnoreCase(controllerName)){
                        continue;
                    }

                    LogsJB.trace("Validara si el contenido es Null: "+controllerName);
                    //Si el contenido es null, continua, no tiene caso hacer el resto
                    Object contenido=(Object) controladorMethod.invoke(this, null);
                    if(Objects.isNull(contenido)){
                        LogsJB.debug("El campo "+controllerName+" no tiene contenido");
                        return false;
                    }else{
                        campos_con_informacion++;
                    }
                    
                }
            }
        }catch (Exception e) {
            LogsJB.fatal("Excepción disparada al verificar que los valores esten llenos: " + e.toString());
            LogsJB.fatal("Tipo de Excepción : " + e.getClass());
            LogsJB.fatal("Causa de la Excepción : " + e.getCause());
            LogsJB.fatal("Mensaje de la Excepción : " + e.getMessage());
            LogsJB.fatal("Trace de la Excepción : " + e.getStackTrace());
        }
        LogsJB.debug("Cantidad de campos que si tienen información: "+campos_con_informacion);
        return true;
    }


}
