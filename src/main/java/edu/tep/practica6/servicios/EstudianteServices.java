/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tep.practica6.servicios;

import edu.tep.practica6.encapsulacion.Estudiante;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase servicio para aplicando patron singlenton
 * Las operaciones con el motor de persistencia...
 * @author vacax
 */
public class EstudianteServices {
    
    private static EstudianteServices estudianteServices;
    
    private List<Estudiante> listaEstudiante =new ArrayList<>();
    
    private EstudianteServices(){
        listaEstudiante.add(new Estudiante(20011136, "Carlos", "Camacho"));
        listaEstudiante.add(new Estudiante(20011137, "Francisco", "Grullón"));
        listaEstudiante.add(new Estudiante(20011138, "Domingo", "Jimenez"));
    }
    
    /**
     * 
     * @return 
     */
    public static EstudianteServices getInstancia(){
        if(estudianteServices == null){
            estudianteServices = new EstudianteServices();
        }
        return estudianteServices;
    }
    
    public void agregarEstudiante(Estudiante estudiante){
        listaEstudiante.add(estudiante);
    }
    
    /**
     * Retorna la lista de los estudiantes registrados..
     * En este caso no usamos base de datos...
     * @return 
     */
    public List<Estudiante> listar(){
        return listaEstudiante;
    }
    
    /**
     * 
     * @param matricula
     * @return 
     */
    public Estudiante getEstudiantePorMatricula(int matricula){
       Estudiante estudiante=null;
       for(Estudiante e : listaEstudiante){
           if(e.getMatricula() == matricula){
               estudiante = e;
               break;
           }
       }
       return estudiante;
    }
    
    /**
     * 
     * @param estudianteModificado
     * @return 
     */
    public Estudiante modificarEstudiante(Estudiante estudianteModificado){
        Estudiante estudiante = getEstudiantePorMatricula(estudianteModificado.getMatricula());
        if(estudiante==null){
            return null; // o generamos una excepcion...
        }
        estudiante.setNombre(estudianteModificado.getNombre());
        estudiante.setApellido(estudianteModificado.getApellido());
        return estudiante;
    }
    
    public boolean borrarEstudiante(int matricula){
        
        Estudiante estudiante = getEstudiantePorMatricula(matricula);
        if(estudiante == null){
            return false;
        }
        
        return listaEstudiante.remove(estudiante);
    }
    
}
