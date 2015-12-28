package modelo;

/**
 *
 * @author Jose
 */
public class Falta extends Penalitzacio {

    private String descripcio;

    public Falta(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

}
