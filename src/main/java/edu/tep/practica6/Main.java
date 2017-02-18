package edu.tep.practica6;

import edu.tep.practica6.servicios.EstudianteServices;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;

public class Main {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EstudianteServices  estudianteServices =  EstudianteServices.getInstancia();

        //debe ir antes de cualquier ruta o controlador definido.    
        //ruta estatica para imagenes, css, JS, etc.
        staticFiles.location("/publico");

        //Manejo de error.
        exception(Exception.class, (exception, request, response) -> {
            // Handle the exception here
            exception.printStackTrace();
        });

        get("/holaMundo", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            //spark/template/freemarker/ --> Ruta por defecto...
            return new ModelAndView(attributes, "holaMundo.ftl");
        }, new FreeMarkerEngine());
        
        get("/listar", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("estudiantes", estudianteServices.listar());

            //spark/template/freemarker/ --> Ruta por defecto...
            return new ModelAndView(attributes, "listar.ftl");
        }, new FreeMarkerEngine());

//        //
//        get("/pruebaPlantilla", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            model.put("message", "Llamando la ruta de forma directa...");
//            return new FreeMarkerEngine().render(
//                    new ModelAndView(model, "plantilla/holaMundo.ftl")
//            );
//        });

    }
}
