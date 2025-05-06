/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.util.UUID;

/**
 *
 * @author OMAR
 */
public class Tarea {
    
    
    private UUID id;
    private String titulo;
    private String Descripcion;
    private LocalDate fechaVencimiento;
    private PrioridadTarea prioridad;
    private EstadoTarea estado;
    
    public Tarea(UUID id, String titulo, String Descripcion,
                LocalDate fechaVencimiento, PrioridadTarea prioridad,
                EstadoTarea estado) {      
        this.id = id;
        this.titulo = titulo;
        this.Descripcion = Descripcion;
        this.fechaVencimiento = fechaVencimiento;
        this.prioridad = prioridad;
        this.estado = estado;
    }
    
    public UUID getId() {        
        return id;
    }

    public void setId(UUID id) {        
        this.id = id;
    }

    public String getTitulo() {       
        return titulo;
    }

    public void setTitulo(String titulo) {        
        this.titulo = titulo;
    }

    public String getDescripcion() {        
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {        
        this.Descripcion = Descripcion;
    }

    public LocalDate getFechaVencimiento() {        
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {        
        this.fechaVencimiento = fechaVencimiento;
    }

    public PrioridadTarea getPrioridad() {        
        return prioridad;
    }

    public void setPrioridad(PrioridadTarea prioridad) {       
        this.prioridad = prioridad;
    }
    
    public int getValorPrioridad(){
        return this.prioridad.getValor();
    }

    public EstadoTarea getEstado() {        
        return estado;
    }

    public void setEstado(EstadoTarea estado) {     
        this.estado = estado;
    }
}
