/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import excepciones.FechaInvalidaException;
import excepciones.TituloInvalidoException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author OMAR
 */
public class LectorEntradas {
    private final Scanner scanner;
    private final ValidadorEntradas validador;
    private final DateTimeFormatter FORMATO_FECHA;

    public LectorEntradas(Scanner scanner, ValidadorEntradas validador,
            DateTimeFormatter FORMATO_FECHA) {
        this.scanner = scanner;
        this.validador = validador;
        this.FORMATO_FECHA = FORMATO_FECHA;
    }
    
    public String leerTitulo(){
        String titulo;
        boolean tituloValido;
        
        do {
            System.out.println("Introduce el titulo de la tarea:");
            titulo = scanner.nextLine();
            try {
                tituloValido = validador.validarTitulo(titulo);
            }catch (TituloInvalidoException ex) {
                System.out.println(ex.getMessage());
                tituloValido = false;
            }
        } while (!tituloValido);
        return titulo;
    }
    
    public String leerDescripcion(){
        String descripcion;
        System.out.println("\n");
        System.out.println("Introduce la descripción de la tarea:");
        descripcion = scanner.nextLine();
        return descripcion;
    }
    
    public LocalDate leerFechaVencimiento(){
        String fechaVencimiento;
        LocalDate fechaConvertida = null;
        boolean fechaValida = false;
        
        do {
            System.out.println("\n");
            System.out.println("introduce la fecha de vencimiento de la tarea(formato: DD-MM-YYYY)");
            fechaVencimiento = scanner.nextLine();
            
            try {
                fechaValida = validador.validarFecha(fechaVencimiento, FORMATO_FECHA);
                fechaConvertida = LocalDate.parse(fechaVencimiento, FORMATO_FECHA);
            }catch (FechaInvalidaException ex) {
                System.out.println(ex.getMessage());
                fechaValida = false;
            }
        } while (!fechaValida);
        
        return fechaConvertida;
    }
    
    public boolean leerConfirmacion() {
        String entrada;
        do {
            entrada = scanner.nextLine().trim();
            
            if (!entrada.equalsIgnoreCase("S") && !entrada.equalsIgnoreCase("N")) {
                System.out.println("Entrada inválida. Por favor, ingresa 'S' para sí o 'N' para no.");
            }
        } while (!entrada.equalsIgnoreCase("S") && !entrada.equalsIgnoreCase("N"));

        return entrada.equalsIgnoreCase("S");
    }
    
    public PrioridadTarea leerPrioridad() {
        while (true) {
            System.out.println("\n");
            System.out.println("Introduzca la prioridad de la tarea:");
            System.out.println("3. Alta");
            System.out.println("2. Media");
            System.out.println("1. Baja");
            int opcion = leerEntero();

            switch (opcion) {
                case 1: return PrioridadTarea.PRIORIDAD_BAJA;
                case 2: return PrioridadTarea.PRIORIDAD_MEDIA;
                case 3: return PrioridadTarea.PRIORIDAD_ALTA;
                default:
                    System.out.println("Opción inválida. Elija entre 1 y 3.");
            }
        }
    }
    
    public EstadoTarea leerEstado() {
        while (true) {
            System.out.println("\n");
            System.out.println("Introduzca el estado de la tarea:");
            System.out.println("3. Pendiente");
            System.out.println("2. En progreso");
            System.out.println("1. Completada");
            int opcion = leerEntero();

            switch (opcion) {
                case 1: return EstadoTarea.ESTADO_COMPLETADA;
                case 2: return EstadoTarea.ESTADO_EN_PROGRESO;
                case 3: return EstadoTarea.ESTADO_PENDIENTE;
                default:
                    System.out.println("Opción inválida. Elija entre 1 y 3.");
            }
        }
    }
    
    public int leerNumeroTarea(){
        System.out.println("Introduce el número de la tarea que quieras actualizar:");
        int numeroTarea = leerEntero();
        return numeroTarea;
    }
    
    public int leerEntero() {
        int numero;
        while (true) {
            try {
                numero = Integer.parseInt(scanner.nextLine());
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            }
        }
    }
    
    public void esperarEnter(){
        scanner.nextLine();
    }
}
