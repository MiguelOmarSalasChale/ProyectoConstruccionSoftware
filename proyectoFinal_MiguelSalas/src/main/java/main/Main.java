/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import modelo.*;

/**
 *
 * @author OMAR
 */
public class Main {
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        iniciarAplicacion();
    }
    
    private static void iniciarAplicacion() {
        DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Scanner SCANNER = new Scanner(System.in);
        
        GestorTareas gestorTareas = new GestorTareas();
        ValidadorEntradas validador = new ValidadorEntradas();
        LectorEntradas lector = new LectorEntradas(SCANNER, validador, 
                                                   FORMATO_FECHA);
        ManejadorConsola consola = new ManejadorConsola(lector, gestorTareas,
                                                        FORMATO_FECHA);
        
        int opcion;
        
        do {
            consola.mostrarMenu();
            opcion = lector.leerEntero();
            
            switch (opcion) {
                case 1:
                    System.out.println("\n");
                    consola.crearTarea();
                    break;
                    
                case 2:
                    System.out.println("\n");
                    consola.listarTareas();
                    break;
                
                case 3:
                    System.out.println("\n");
                    consola.actualizarTareas();
                    break;
                
                case 4:
                    System.out.println("\n");
                    consola.eliminarTareas();
                    break;
                    
                case 5:
                    System.out.println("Saliendo...");
                    break;
                    
                default:
                    System.out.println("Selecciona una opcion válida, presione enter para continuar");
                    consola.limpiarPantalla();
                    break;
            }
        } while (opcion != 5);
    }
}
