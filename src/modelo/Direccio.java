package modelo;

/**
 *
 * @author Jose
 */
public class Direccio {

    private String pais;
    private String ciutat;
    private String carrer;
    private int numero;
    private String cp;

    /**
     * Constructor buit.
     */
    public Direccio() {
    }

    /**
     * Constructor simple.
     *
     * @param pais
     * @param ciutat
     * @param carrer
     * @param numero
     * @param cp
     */
    public Direccio(String pais, String ciutat, String carrer, int numero, String cp) {
        this.pais = pais;
        this.ciutat = ciutat;
        this.carrer = carrer;
        this.numero = numero;
        this.cp = cp;
    }

    /**
     * Constructor XML
     *
     * @param ciutat
     * @param carrer
     * @param numero
     * @param cp codigo postal
     */
    public Direccio(String ciutat, String carrer, int numero, String cp) {
        this.ciutat = ciutat;
        this.carrer = carrer;
        this.numero = numero;
        this.cp = cp;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public String getCarrer() {
        return carrer;
    }

    public void setCarrer(String carrer) {
        this.carrer = carrer;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCP() {
        return cp;
    }

    public void setCP(String cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "Ciudad: " + this.ciutat + "\n"
                + "Calle: " + this.carrer + "\n"
                + "Numero: " + this.numero + "\n"
                + "CP: " + this.cp + "\n";
    }

}
