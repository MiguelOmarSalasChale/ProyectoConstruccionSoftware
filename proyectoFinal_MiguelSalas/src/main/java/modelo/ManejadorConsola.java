/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author OMAR
 */
public class ManejadorConsola {
    private LectorEntradas lector;
    private GestorTareas gestorTareas;
    private final DateTimeFormatter FORMATO_FECHA;

    public ManejadorConsola(LectorEntradas lector, GestorTareas gestorTareas,
            DateTimeFormatter FORMATO_FECHA) {
        this.lector = lector;
        this.gestorTareas = gestorTareas;
        this.FORMATO_FECHA = FORMATO_FECHA;
    }
    
    public void mostrarMenu() {
        System.out.println("\n--- Gestor de Tareas ---");
        System.out.println("1. Crear tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Actualizar tarea");
        System.out.println("4. Eliminar Tarea");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    public void crearTarea() {

        String titulo = lector.leerTitulo();
        String descripcion = lector.leerDescripcion();
        LocalDate fechaVencimiento = lector.leerFechaVencimiento();
        PrioridadTarea prioridad = lector.leerPrioridad();
        EstadoTarea estado = lector.leerEstado();
        
        gestorTareas.crearTarea(titulo, descripcion,
                fechaVencimiento, prioridad, estado);
        
        System.out.println("\n");
        System.out.println("Tarea creada con éxito, presione enter para continuar.");
        limpiarPantalla();
    }
    
    public void listarTareas() {
        Filtro filtro = null;
        Orden orden = null;
        
        while (filtro == null) {
            System.out.println("¿Cómo deseas filtrar las tareas?");
            System.out.println("1. Por prioridad");
            System.out.println("2. Por fecha");
            System.out.print("Ingresa el número de la opción deseada: ");
            int opcion = lector.leerEntero();
            
            switch (opcion) {
                case 1:
                    filtro = Filtro.FILTRO_PRIORIDAD;
                    break;
                case 2:
                    filtro = Filtro.FILTRO_FECHA;
                    break;
                default:
                    System.out.println("Opción inválida. Intenta nuevamente.");
                    System.out.println("\n");
            }
        }
        
        System.out.println("\n");
        while (orden == null) {
            System.out.println("¿En qué orden deseas ver las tareas?");
            System.out.println("1. Ascendente");
            System.out.println("2. Descendente");
            System.out.print("Ingresa el número de la opción deseada: ");
            int opcion = lector.leerEntero();

            switch (opcion) {
                case 1:
                    orden = Orden.ORDEN_ASCENDENTE;
                    break;
                case 2:
                    orden = Orden.ORDEN_DESCENDENTE;
                    break;
                default:
                    System.out.println("Opción inválida. Intenta nuevamente.");
                    System.out.println("\n");
            }
        }
        
        HashMap<Integer, UUID> idTareas = gestorTareas.listarTareas(filtro, orden);
        
        System.out.println("\n");
        if (idTareas.isEmpty()) {
            System.out.println("No hay tareas disponibles para mostrar, presione enter para continuar.");
            limpiarPantalla();
            return;
        }
        
        imprimirTareas(idTareas);
        System.out.println("\n");
        System.out.println("presione enter para continuar");
        limpiarPantalla();
    }
    
    public void actualizarTareas() {
        UUID idTarea = seleccionarTarea();
        if (idTarea == null){
            System.out.println("\n");
            System.out.println("No hay tareas disponibles para realizar esta acción presione enter para continuar.");
            limpiarPantalla();
            return;
        }
        
        Tarea tarea = gestorTareas.obtenerTareaPorId(idTarea);

        System.out.println("\n");
        String nuevoTitulo;
        if (confirmarCambio("título", tarea.getTitulo())) {
            nuevoTitulo = lector.leerTitulo();
        }else{
            nuevoTitulo = tarea.getTitulo();
        }

        System.out.println("\n");
        String nuevaDescripcion;
        if (confirmarCambio("descripción", tarea.getDescripcion())) {
            nuevaDescripcion = lector.leerDescripcion();
        }else{
            nuevaDescripcion = tarea.getDescripcion();
        }

        System.out.println("\n");
        LocalDate nuevaFechaVencimiento;
        if (confirmarCambio("fecha de vencimiento", tarea.getFechaVencimiento().format(FORMATO_FECHA))) {
            nuevaFechaVencimiento = lector.leerFechaVencimiento();
        }else{
            nuevaFechaVencimiento = tarea.getFechaVencimiento();
        }

        System.out.println("\n");
        PrioridadTarea nuevaPrioridad;
        if (confirmarCambio("prioridad", tarea.getPrioridad().toString())) {
            nuevaPrioridad = lector.leerPrioridad();
        }else {
            nuevaPrioridad = tarea.getPrioridad();
        }

        System.out.println("\n");
        EstadoTarea nuevoEstado;
        if (confirmarCambio("estado", tarea.getEstado().toString())) {
            nuevoEstado = lector.leerEstado();
        }else {
            nuevoEstado = tarea.getEstado();
        }

        gestorTareas.actualizarTarea(idTarea, nuevoTitulo, nuevaDescripcion,
                nuevaFechaVencimiento, nuevaPrioridad, nuevoEstado);

        System.out.println("\n");
        System.out.println("Tarea actualizada, presione enter para continuar.");
        limpiarPantalla();
            
    }
    
    public void eliminarTareas(){
        UUID idTarea = seleccionarTarea();
        if (idTarea == null){
            System.out.println("\n");
            System.out.println("No hay tareas disponibles para realizar esta acción presione enter para continuar.");
            limpiarPantalla();
            return;
        }
        
        System.out.println("\n");
        System.out.println("¿Estás seguro de que deseas eliminar esta tarea? (S/N)");
        boolean confirmacion = lector.leerConfirmacion();
        if (confirmacion) {
            gestorTareas.eliminarTarea(idTarea);
            System.out.println("\n");
            System.out.println("Tarea eliminada, presione enter para continuar.");
            limpiarPantalla();
        } else {
            System.out.println("\n");
            System.out.println("Eliminación cancelada, presione enter para continuar.");
            limpiarPantalla();
        }
    }
    
    public void limpiarPantalla(){
        lector.esperarEnter();
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    private boolean confirmarCambio(String campo, String valorActual) {
        System.out.printf("¿Deseas cambiar el %s? (actual: %s) (S/N):", campo, valorActual);
        return lector.leerConfirmacion();
    }
    
    private void imprimirTareas(HashMap<Integer, UUID> idTareas) {
        System.out.printf("%-5s %-20s %-20s %-10s %-15s %-15s%n",
                          "No.", "Título", "descripción", "Fecha Venc.", 
                          "Prioridad", "Estado");
        System.out.println("---------------------------------------------------------------------");
        for (int indice: idTareas.keySet()){
            UUID idTarea = idTareas.get(indice);
            Tarea tarea = gestorTareas.obtenerTareaPorId(idTarea); 
            
            System.out.printf("%-5s %-20s %-20s %-10s %-15s %-15s%n",
                indice,
                tarea.getTitulo(),
                tarea.getDescripcion(),
                tarea.getFechaVencimiento().format(FORMATO_FECHA),
                tarea.getPrioridad(),
                tarea.getEstado());
        }
    }
    
    private UUID seleccionarTarea(){
        HashMap<Integer, UUID> idTareas = 
                gestorTareas.listarTareas(Filtro.FILTRO_FECHA, Orden.ORDEN_ASCENDENTE);
        
        if (idTareas.isEmpty()) {
            return null;
        }else{
            imprimirTareas(idTareas);
            int seleccion = lector.leerNumeroTarea();

            while (!idTareas.containsKey(seleccion)) {
                System.out.println("\n");
                System.out.println("Selecciona una opción válida, presione enter para continuar.");
                limpiarPantalla();
                imprimirTareas(idTareas);
                System.out.println("Introduce el número de la tarea que quieras actualizar:");
                seleccion = lector.leerNumeroTarea();
            }

            UUID idTarea = idTareas.get(seleccion);
            return idTarea;
        } 
    }
    
}
