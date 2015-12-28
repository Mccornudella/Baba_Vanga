package modelo;

/**
 *
 * @author Jose
 */
public class Endarrediment extends Penalitzacio {

    private int retras;

    public Endarrediment(int retras) {
        this.retras = retras;
    }

    public Endarrediment() {
        retras = 0;
    }

    public void setRetras(int retras) {
        this.retras = retras;
        calcularCost();
    }

    public int getRetras() {
        return retras;
    }

    public void calcularCost() {
        double preu = retras * 2;
        super.setCost(preu);

    }

}
