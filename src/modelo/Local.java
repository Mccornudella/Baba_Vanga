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
    private int capacitat_max;
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
        this.capacitat_max = capacitat_max;
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
        this.capacitat_max = capacitat_max;
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
        return capacitat_max;
    }

    public void setCapacitatMax(int capacitat_max) {
        this.capacitat_max = capacitat_max;
    }
    
    public Moto escollirMoto(){
        Iterator it = motos.iterator();
        ArrayList<Moto> motos_disponibles = new ArrayList();
        while(it.hasNext()){
            Moto m = (Moto) it.next();
            if (m.getEstat()){
                motos_disponibles.add(m);
            }
        }
        Interficie.imprimirLista(motos_disponibles);
        int posMoto = Interficie.selNumLista(motos_disponibles);
        Moto moto1 = motos_disponibles.get(posMoto);
        return moto1;
    } 
    
    public boolean compCapacidad(int cantidad){
        return true;
    }

    public String getIDGerent() {
        return IDGerent;
    }
    
    
    public boolean compDisponibilidad(int cantidad){
        return true;
    }
    
    public ArrayList agafarMotos(int cantidad){
        return null;
    }
    
    public void afegirMotos(int motos){
        
    }
    
    public void veureMotos(){
        Interficie.imprimirLista(motos);
    }

    @Override
    public String toString() {
        String str = direccio.toString() + "------------\n";      
        return str;
    }
}
