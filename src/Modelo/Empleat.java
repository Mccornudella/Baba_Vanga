package modelo;

import java.io.Serializable;

/**
 * @author rob3ns
 */

public class Empleat extends Usuari implements Serializable{

    private String correo;
    private int numeroPers;

    /**
     * Constructor simple, solo parámetros de empleado.
     * @param correo
     * @param numeroPers Teléfono.
     */
    public Empleat(String correo, int numeroPers) {
        this.correo = correo;
        this.numeroPers = numeroPers;
    }
    
    /**
     * Constructor solo parámetros de usuario.
     * @param username
     * @param password
     * @param nombre
     * @param apellidos 
     */
    public Empleat(String username, String password, String nombre, String apellidos) {
        super(username, password, nombre, apellidos);
    }

    /**
     * Constructor con parámetros de usuario y empleado.
     * @param correo
     * @param numeroPers
     * @param username
     * @param password
     * @param nombre
     * @param apellidos 
     */
    public Empleat(String correo, int numeroPers, String username, String password, String nombre, String apellidos) {
        super(username, password, nombre, apellidos);
        this.correo = correo;
        this.numeroPers = numeroPers;
    }
    
    /**
     * Constructor vacío.
     */
    public Empleat() {
        super(null, null, null, null);
    }     

    public String getCorreo() {
        return correo;
    }

    public int getNumeroPers() {
        return numeroPers;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNumeroPers(int numeroPers) {
        this.numeroPers = numeroPers;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
