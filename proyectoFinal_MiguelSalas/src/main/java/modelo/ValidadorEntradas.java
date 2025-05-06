/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import excepciones.FechaInvalidaException;
import excepciones.TituloInvalidoException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author OMAR
 */
public class ValidadorEntradas {
    
    public boolean validarTitulo(String titulo) throws TituloInvalidoException {
        if(!(titulo.isBlank() || titulo.isEmpty())){
            return true;
        }else {
            throw new TituloInvalidoException("El titulo no puede estar vacío");
        }
    }
    
    public boolean validarFecha(String fechaAValidar, DateTimeFormatter formato)
                                throws FechaInvalidaException {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fecha;
        boolean fechaVacia = (fechaAValidar.isBlank() || fechaAValidar.isEmpty());
        if(!fechaVacia){
            try {
            fecha = LocalDate.parse(fechaAValidar, formato);
            }catch (Exception e) {
                throw new FechaInvalidaException("La fecha debe ser en formato DD-MM-YYYY");
            }
            
            if(fecha.isEqual(fechaActual) || fecha.isAfter(fechaActual)){
                return true;
            }else {
                throw new FechaInvalidaException("La fecha debe ser hoy o futura");
            }
        }else {
            throw new FechaInvalidaException("La fecha no puede estar vacía");
        }
    }
}
