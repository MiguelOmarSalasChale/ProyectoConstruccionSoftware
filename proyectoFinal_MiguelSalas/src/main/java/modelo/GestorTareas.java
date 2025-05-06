/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.HashMap;
import java.util.UUID;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author OMAR
 */
public class GestorTareas {
    
    
    private HashMap<UUID, Tarea> tareas;
    
    public GestorTareas() {
        this.tareas = new HashMap<>();
    }
    
    public void crearTarea(String titulo, String descripcion, 
                          LocalDate fechaVencimiento, PrioridadTarea prioridad, 
                          EstadoTarea estado) {
        UUID idNuevaTarea = generarId();
        Tarea nuevaTarea = new Tarea(idNuevaTarea, titulo, descripcion,
                                fechaVencimiento, prioridad, estado);
        tareas.put(idNuevaTarea, nuevaTarea);
    }
    
    public void actualizarTarea(UUID idTarea, String nuevoTitulo, String nuevaDescripcion, 
                          LocalDate nuevaFechaVencimiento, PrioridadTarea nuevaPrioridad, 
                          EstadoTarea nuevoEstado) {
        Tarea tarea = tareas.get(idTarea);
        tarea.setTitulo(nuevoTitulo);
        tarea.setDescripcion(nuevaDescripcion);
        tarea.setFechaVencimiento(nuevaFechaVencimiento);
        tarea.setPrioridad(nuevaPrioridad);
        tarea.setEstado(nuevoEstado);
    }
    
    public void eliminarTarea(UUID idTarea) {
        tareas.remove(idTarea);
    }
    
    public HashMap<Integer, UUID> listarTareas(Filtro filtro, Orden orden) {
        HashMap<Integer, UUID> idTareasOrdenadas = new HashMap<>();

        switch (filtro) {
            case FILTRO_FECHA:
                idTareasOrdenadas = ordenarFecha(orden);
                break;

            case FILTRO_PRIORIDAD:
                idTareasOrdenadas = ordenarPrioridad(orden);
                break;

            default:
                throw new IllegalArgumentException("Filtro no soportado: " + filtro);
        }
        
        return idTareasOrdenadas;
    }
    
    public Tarea obtenerTareaPorId(UUID idTarea){
        return tareas.get(idTarea);
    }
    
    private UUID generarId() {
        UUID nuevoId = UUID.randomUUID();
        while (tareas.containsKey(nuevoId)){
            nuevoId = UUID.randomUUID();
        }
        return nuevoId;
    }
    
    private HashMap<Integer, UUID> ordenarFecha(Orden orden) {
        ArrayList<Tarea> tareasDesordenadas = new ArrayList<>();
        
        for (UUID idTarea: tareas.keySet()){
            Tarea tarea = tareas.get(idTarea);
            tareasDesordenadas.add(tarea);
        }
        
        switch (orden){
            case ORDEN_ASCENDENTE:
                Collections.sort(tareasDesordenadas, 
                        Comparator.comparing(Tarea::getFechaVencimiento));
                break;
                
            case ORDEN_DESCENDENTE:
                Collections.sort(tareasDesordenadas, 
                        Comparator.comparing(Tarea::getFechaVencimiento).reversed());
                break;
            
            default:
                throw new IllegalArgumentException("Orden no soportado: " + orden);

        }
        
        HashMap<Integer, UUID> idTareasOrdenadas = new HashMap<>();
        
        int contadorTareas = 1;
        for(Tarea tarea: tareasDesordenadas){
            idTareasOrdenadas.put(contadorTareas, tarea.getId());
            contadorTareas++;
        }
        
        return idTareasOrdenadas;
    }
    
    private HashMap<Integer, UUID> ordenarPrioridad(Orden orden) {
        ArrayList<Tarea> tareasDesordenadas = new ArrayList<>();
        
        for (UUID idTarea: tareas.keySet()){
            Tarea tarea = tareas.get(idTarea);
            tareasDesordenadas.add(tarea);
        }
        
        switch (orden){
            case ORDEN_ASCENDENTE:
                Collections.sort(tareasDesordenadas, 
                        Comparator.comparingInt(Tarea::getValorPrioridad));
                break;
                
            case ORDEN_DESCENDENTE:
                Collections.sort(tareasDesordenadas, 
                        Comparator.comparingInt(Tarea::getValorPrioridad).reversed());
                break;
                
            default:
                throw new IllegalArgumentException("Orden no soportado: " + orden);

        }
        
        HashMap<Integer, UUID> idTareasOrdenadas = new HashMap<>();
        
        int contadorTareas = 1;
        for(Tarea tarea: tareasDesordenadas){
            idTareasOrdenadas.put(contadorTareas, tarea.getId());
            contadorTareas++;
        }
        
        return idTareasOrdenadas;
    }
}
