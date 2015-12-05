package modelo;

import java.io.Serializable;

/**
 * @author rob3ns
 */
public class Admin extends Empleat implements Serializable{

    public Admin() {
    }

    public Admin(String correo, int numeroPers) {
        super(correo, numeroPers);
    }

    public Admin(String username, String password, String nombre, String apellidos) {
        super(username, password, nombre, apellidos);
    }

    public Admin(String correo, int numeroPers, String username, String password, String nombre, String apellidos) {
        super(correo, numeroPers, username, password, nombre, apellidos);
    }

    @Override
    public String toString() {
        return "Admin: " + super.toString();
    }

    
}
