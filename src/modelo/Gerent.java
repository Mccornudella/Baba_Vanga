package modelo;

import java.io.Serializable;
import vista.Interficie;

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
     * @param correo correo electronico del gerente
     * @param ID
     */
    public Gerent(String correo, String ID) {
        super(correo, ID);
    }

    /**
     * Constructor solo parámetros de usuario.
     *
     * @param ID
     * @param username usuari en el sistema del gerente
     * @param password password del gerente
     * @param nombre nombre del gerente
     * @param apellidos apellidos del gerente
     */
    public Gerent(String username, String password, String nombre, String apellidos, String ID) {
        super(username, password, nombre, apellidos);
        this.ID = ID;
    }

    /**
     * Constructor con parámetros de usuario y empleado.
     *
     * @param correo correo electronico del gerente
     * @param ID
     * @param username usuari en el sistema del gerente
     * @param password password del gerente
     * @param nombre nombre del gerente
     * @param apellidos apellidos del gerente
     */
    public Gerent(String correo, String ID, String username, String password, String nombre, String apellidos) {
        super(correo, ID, username, password, nombre, apellidos);
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Gerente: " + super.toString();
    }

}
