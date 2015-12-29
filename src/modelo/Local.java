package modelo;

import vista.Interficie;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Jose
 */
public class Local {

    private ArrayList<Moto> motos;
    private Direccio direccio;
    private int capacitatMax;
    private String IDGerent;

    /**
     * Constructor buit.
     */
    public Local() {

    }

    /**
     * Constructor sense llista de motos.
     *
     * @param direccio
     * @param capacitat_max
     */
    public Local(Direccio direccio, int capacitat_max, String IDGerent) {
        this.direccio = direccio;
        this.capacitatMax = capacitat_max;
        motos = new ArrayList<>(0);
        this.IDGerent = IDGerent;
    }

    /**
     * Constructor amb lista de motos.
     *
     * @param motos
     * @param direccio
     * @param capacitat_max
     */
    public Local(ArrayList<Moto> motos, Direccio direccio, int capacitat_max) {
        this.motos = motos;
        this.direccio = direccio;
        this.capacitatMax = capacitat_max;
    }

    public ArrayList getMotos() {
        return motos;
    }

    public void setMotos(ArrayList<Moto> motos) {
        this.motos = motos;
    }

    public Direccio getDireccio() {
        return direccio;
    }

    public void setDireccio(Direccio direccio) {
        this.direccio = direccio;
    }

    public int getCapacitatMax() {
        return capacitatMax;
    }

    public void setCapacitatMax(int capacitat_max) {
        this.capacitatMax = capacitat_max;
    }

    /**
     * Muestra una lista de las motos disponibles y selecciona una.
     *
     * @return Moto seleccionada.
     */
    public Moto escollirMoto() {
        Iterator it = motos.iterator();
        ArrayList<Moto> motos_disponibles = new ArrayList();
        while (it.hasNext()) {
            Moto m = (Moto) it.next();
            boolean estat = m.getEstat();
            if (estat) {
                motos_disponibles.add(m);
            }
        }
        Interficie.imprimirLista(motos_disponibles);
        int posMoto = Interficie.selNumLista(motos_disponibles);
        Moto moto1 = motos_disponibles.get(posMoto);
        return moto1;
    }

    /**
     * Comprueba la capacidad máx de un local.
     *
     * @param cantidad Cantidad a añadir.
     * @return True si puede almacenar esas motos sin pasar del 75%.
     */
    public boolean compCapacidad(int cantidad) {
        int cantMax = (int) (capacitatMax * 0.75f);
        return (motos.size() + cantidad) < cantMax;
    }

    public String getIDGerent() {
        return IDGerent;
    }

    /**
     * Comprueba la disponibilidad de un local.
     *
     * @param cantidad Cantidad a retirar.
     * @return True si puede retirar esa cantidad de motos sin quedarse con
     * menos de 5.
     */
    public boolean compDisponibilidad(int cantidad) {
        return (motos.size() - cantidad) > 5;
    }

    public void veureEstat() {
        String info = "Capacidad máx: " + this.capacitatMax;
        info += "\nCantidad de motos: " + this.motos.size();
        info += "\nPorcentaje: " + (this.motos.size() * 100) / this.capacitatMax;
        Interficie.escriu(info);
    }

    public ArrayList agafarMotos(int cantidad) {
        ArrayList<Moto> mot = new ArrayList();
        int i = 0;
        while (i < cantidad && !this.motos.isEmpty()) {
            mot.add(this.motos.get(0));
            this.motos.remove(0);
            i++;
        }
        return mot;
    }

    public void afegirMotos(ArrayList<Moto> Mot) {
        for (Moto m : Mot) {
            this.motos.add(m);
        }
    }

    public void veureMotos() {
        for (Moto m : motos) {
            Interficie.escriu("ID: " + m.getID());
            Interficie.escriu("Matricula: " + m.getMatricula());
            EspecMoto esp = m.getEspec();
            Interficie.escriu("Color: " + esp.getColor());
            Interficie.escriu("Marca: " + esp.getMarca());
            Interficie.escriu("Model: " + esp.getModel());
            Interficie.escriu("Cilindrada: " + esp.getCilindrada() + "\n");
        }
    }

    @Override
    public String toString() {
        String str = direccio.toString() + "------------\n";
        return str;
    }
}
