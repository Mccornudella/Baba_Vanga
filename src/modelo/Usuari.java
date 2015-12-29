package modelo;

import java.io.Serializable;

/**
 * @author rob3ns
 */
public class Usuari implements Serializable {

    protected String username;
    protected String password;
    protected String nom;
    protected String cognoms;

    /**
     * Constructor simple.
     *
     * @param username
     * @param password
     * @param nombre
     * @param apellidos Ambos cognoms separados por un espacio.
     */
    public Usuari(String username, String password, String nombre, String apellidos) {
        this.username = username;
        this.password = password;
        this.nom = nombre;
        this.cognoms = apellidos;
    }

    public Usuari(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor vacío.
     */
    public Usuari() {
    }

    public String getApellidos() {
        return cognoms;
    }

    public String getNombre() {
        return nom;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setApellidos(String apellidos) {
        this.cognoms = apellidos;
    }

    public void setNombre(String nombre) {
        this.nom = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean checkLogin(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public String toString() {
        return username;
    }
}
