package modelo;

import java.io.Serializable;

/**
 * @author rob3ns
 */
public class Admin extends Empleat implements Serializable {

    public Admin() {
    }

    public Admin(String correo, String ID) {
        super(correo, ID);
    }

    public Admin(String username, String password, String nombre, String apellidos, String ID) {
        super(username, password, nombre, apellidos);
        this.ID = ID;
    }

    public Admin(String correo, String ID, String username, String password, String nombre, String apellidos) {
        super(correo, ID, username, password, nombre, apellidos);
    }

    @Override
    public String toString() {
        return "Admin: " + super.toString();
    }

}
