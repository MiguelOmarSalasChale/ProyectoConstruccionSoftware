/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package modelo;

/**
 *
 * @author OMAR
 */
public enum PrioridadTarea {
    PRIORIDAD_ALTA(3), PRIORIDAD_MEDIA(2), PRIORIDAD_BAJA(1);
    
    private final int valor;
    
    PrioridadTarea(int valor){
        this.valor = valor;
    }
    
    public int getValor(){
        return this.valor;
    }
}
