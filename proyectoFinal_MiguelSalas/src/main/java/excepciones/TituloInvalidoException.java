/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package excepciones;

/**
 *
 * @author OMAR
 */
public class TituloInvalidoException extends Exception {

    /**
     * Creates a new instance of <code>TituloInvalidoException</code> without
     * detail message.
     */
    public TituloInvalidoException() {
    }

    /**
     * Constructs an instance of <code>TituloInvalidoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TituloInvalidoException(String msg) {
        super(msg);
    }

}
