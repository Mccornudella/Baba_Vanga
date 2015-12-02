package Modelo;

import java.io.Serializable;

/**
 * @author rob3ns
 */
public class Gerent extends Empleat implements Serializable {
    
    private Local local;
    
    public Gerent(Local local) {
        this.local = local;
    }

    /**
     * Constructor solo parámetros de empleado.
     *
     * @param correo
     * @param numeroPers
     */
    public Gerent(String correo, int numeroPers) {
        super(correo, numeroPers);
    }

    /**
     * Constructor solo parámetros de usuario.
     *
     * @param username
     * @param password
     * @param nombre
     * @param apellidos
     */
    public Gerent(String username, String password, String nombre, String apellidos) {
        super(username, password, nombre, apellidos);
    }

    /**
     * Constructor con parámetros de usuario y empleado.
     *
     * @param correo
     * @param numeroPers
     * @param username
     * @param password
     * @param nombre
     * @param apellidos
     */
    public Gerent(String correo, int numeroPers, String username, String password, String nombre, String apellidos) {
        super(correo, numeroPers, username, password, nombre, apellidos);
    }

    @Override
    public String toString() {
        return "Gerente: " + super.toString();
    }
}
