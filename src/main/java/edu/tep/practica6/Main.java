package edu.tep.practica6;

import edu.tep.practica6.encapsulacion.Estudiante;
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

        //Un servicio... // MVC
        EstudianteServices estudianteServices = EstudianteServices.getInstancia();

        //debe ir antes de cualquier ruta o controlador definido.    
        //ruta estatica para imagenes, css, JS, etc.
        staticFiles.location("/publico");

        //Manejo de error.
        exception(Exception.class, (exception, request, response) -> {
            // Handle the exception here
            exception.printStackTrace();
        });

        //Redireccionando al controlador..
        get("/", (request, response) -> {
            response.redirect("/listar");
            return "";
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

        //Redireccionando al controlador..
        post("/procesarNuevoEstudiante", (request, response) -> {
            //
            int matricula = Integer.parseInt(request.queryParams("matricula"));
            String nombre = request.queryParams("nombre");
            String apellido = request.queryParams("apellido");
            //Lo ideal es validar... si existe un error devolver al formulario...
            //.... TODO://///
            // Procesar el estudiante.
            Estudiante estudiante = new Estudiante(matricula, nombre, apellido); // creando un objeto..
            estudianteServices.agregarEstudiante(estudiante);

            //redirecciono a la lista...
            response.redirect("/listar");
            return "";
        });

        //Tomo el parametro de la matricula desde la URL, con los :
        get("/editar/:matricula", (request, response) -> {
            //capturando la matricula del estudiante.
            int matricula = Integer.parseInt(request.params("matricula"));
            //Con la matricula busco el objeto...
            Estudiante estudiante = estudianteServices.getEstudiantePorMatricula(matricula);
            if (estudiante == null) { //validación...
                //enviar mensaje de error al listar...
                response.redirect("/listar");
            }

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("estudiante", estudiante);
            attributes.put("accionFormulario", "Editar");
            attributes.put("accion", 1); //borrando...
            attributes.put("urlAccion", "/procesarEditarEstudiante"); //borrando..

            //spark/template/freemarker/ --> Ruta por defecto...
            return new ModelAndView(attributes, "estudiante.ftl");
        }, new FreeMarkerEngine());
       

        post("/procesarEditarEstudiante", (request, response) -> {
            //
            int matricula = Integer.parseInt(request.queryParams("matricula"));
            String nombre = request.queryParams("nombre");
            String apellido = request.queryParams("apellido");
            //Lo ideal es validar... si existe un error devolver al formulario...
            //.... TODO://///
            // Procesar el estudiante.
            Estudiante estudiante = new Estudiante(matricula, nombre, apellido); // creando un objeto..
            estudianteServices.modificarEstudiante(estudiante);

            //redirecciono a la lista...
            response.redirect("/listar");
            return "";
        });
        
        
        //Tomo el parametro de la matricula desde la URL, con los :
        get("/borrar/:matricula", (request, response) -> {
            //capturando la matricula del estudiante.
            int matricula = Integer.parseInt(request.params("matricula"));
            //Con la matricula busco el objeto...
            Estudiante estudiante = estudianteServices.getEstudiantePorMatricula(matricula);
            if (estudiante == null) { //validación...
                //enviar mensaje de error al listar...
                response.redirect("/listar");
            }

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("estudiante", estudiante);
            attributes.put("accionFormulario", "Borrar");
            attributes.put("accion", 2); //borrando...
            attributes.put("urlAccion", "/procesarBorrarEstudiante"); //borrando..

            //spark/template/freemarker/ --> Ruta por defecto...
            return new ModelAndView(attributes, "estudiante.ftl");
        }, new FreeMarkerEngine());
        
        
        
        post("/procesarBorrarEstudiante", (request, response) -> {
            //
            int matricula = Integer.parseInt(request.queryParams("matricula"));           
            //Lo ideal es validar... si existe un error devolver al formulario...
            //.... TODO://///
            // Procesar el estudiante.
            estudianteServices.borrarEstudiante(matricula);

            //redirecciono a la lista...
            response.redirect("/listar");
            return "";
        });

    }

}
