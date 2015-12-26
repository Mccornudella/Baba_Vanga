package modelo;

import java.util.Date;

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
    private boolean estadoMoto;

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

    public void setEstadoMoto(boolean estadoMoto) {
        this.estadoMoto = estadoMoto;
    }


    public boolean isActiva() {
        return activa;
    }

    public boolean isEstadoMoto() {
        return estadoMoto;
    }
    
    public void apuntarEndarrediment(Date retras){
        
    }

    public Falta getFalta() {
        return falta;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
