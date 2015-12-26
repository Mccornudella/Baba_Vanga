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
     * @param numeroPers numero de personal del gerente
     */
    public Gerent(String correo, int numeroPers) {
        super(correo, numeroPers);
    }

    /**
     * Constructor solo parámetros de usuario.
     *
     * @param username usuari en el sistema del gerente
     * @param password password del gerente
     * @param nombre nombre del gerente
     * @param apellidos apellidos del gerente
     */
    public Gerent(String username, String password, String nombre, String apellidos) {
        super(username, password, nombre, apellidos);
    }

    /**
     * Constructor con parámetros de usuario y empleado.
     *
     * @param correo correo electronico del gerente
     * @param numeroPers numero de personal del gerente
     * @param username usuari en el sistema del gerente
     * @param password password del gerente
     * @param nombre nombre del gerente
     * @param apellidos apellidos del gerente
     */
    public Gerent(String correo, int numeroPers, String username, String password, String nombre, String apellidos) {
        super(correo, numeroPers, username, password, nombre, apellidos);
    }

    @Override
    public String toString() {
        return "Gerente: " + super.toString();
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public void entregarMoto() {
        Interficie.imprimirLista(local.getMotos());
        int indice = Interficie.selNumLista(local.getMotos());
        Moto m = (Moto) local.getMotos().get(indice);
        
        if (m.disponible()) {
        Interficie.escriu("Moto entregda! \nMatricula: " + m.getMatricula());
        m.setEstat(true);
        }
        else {
            Interficie.escriu("La moto no está disponible!");
        }
    }
}
