package modelo;

/**
 *
 * @author Jose
 */
public class EstadoReserva {

    private boolean activa;
    private boolean finalitzada;
    private Endarrediment retras;
    private Falta falta;

    public EstadoReserva(boolean activa, boolean finalitzada) {
        this.activa = activa;
        this.finalitzada = finalitzada;
        falta = new Falta("");
        retras = new Endarrediment();
    }

    /**
     * Constructor vacio.
     */
    public EstadoReserva() {
        this.activa = false;
        this.finalitzada = false;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public boolean isFinalitzada() {
        return finalitzada;
    }

    public void setFinalitzada(boolean finalitzada) {
        this.finalitzada = finalitzada;
    }

    public boolean isActiva() {
        return activa;
    }
    
    public void apuntarEndarrediment(int retras) {
        this.retras.setRetras(retras);
    }

    public double getCostRetras() {
        return retras.getCost();
    }

    public void setFalta(Double importe, String desperfecte) {
        falta.setCost(importe);
        falta.setDescripcio(desperfecte);
    }

    public Falta getFalta() {
        return falta;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
