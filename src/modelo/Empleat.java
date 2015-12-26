package modelo;

import java.io.Serializable;

/**
 * @author rob3ns
 */

public class Empleat extends Usuari implements Serializable{

    protected String correo;
    protected String ID;

    /**
     * Constructor simple, solo parámetros de empleado.
     * @param correo
     * @param numeroPers Teléfono.
     */
    public Empleat(String correo, String ID) {
        this.correo = correo;
        this.ID = ID;
    }
    
    /**
     * Constructor solo parámetros de usuario.
     * @param username
     * @param password
     * @param nombre
     * @param apellidos 
     * @param ID 
     */
    public Empleat(String username, String password, String nombre, String apellidos) {
        super(username, password, nombre, apellidos);
    }

    /**
     * Constructor con parámetros de usuario y empleado.
     * @param correo
     * @param ID
     * @param username
     * @param password
     * @param nombre
     * @param apellidos 
     */
    public Empleat(String correo, String ID, String username, String password, String nombre, String apellidos) {
        super(username, password, nombre, apellidos);
        this.correo = correo;
        this.ID = ID;
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

    public String getID() {
        return ID;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
