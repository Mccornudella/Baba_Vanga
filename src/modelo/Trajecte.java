package modelo;

/**
 *
 * @author Jose
 */
public class Trajecte {

    private Local inici;
    private Local fi;

    /**
     * Constructor buit.
     */
    public Trajecte() {

    }

    /**
     * Constructor simple.
     *
     * @param inici Local inicio
     * @param fi Local fin
     */
    public Trajecte(Local inici, Local fi) {
        this.inici = inici;
        this.fi = fi;
    }

    public Local getInici() {
        return inici;
    }

    public void setInici(Local inici) {
        this.inici = inici;
    }

    public Local getFinal() {
        return fi;
    }

    public void setFinal(Local fi) {
        this.fi = fi;
    }
}
