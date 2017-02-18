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
    
    public List<Estudiante> listar(){
        return listaEstudiante;
    }
    
    //
    
}
