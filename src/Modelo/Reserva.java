package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Jose
 */
public class Reserva {

    private final int precioHora = 1;
    private final int precioDia = 15;

    private double preu;
    private int codi;
    private Date inici;
    private Date fi;
    private Trajecte trajecte;
    private Moto moto;
    private EstadoReserva estado;

    /**
     * Constructor buit.
     */
    public Reserva() {

    }

    /**
     * Constructor simple.
     *
     * @param inici fecha de inicio
     * @param fi fecha en la que termina
     * @param trajecte trayecto. Contiene local inicio y fin
     * @param moto moto reservada
     */
    public Reserva(Date inici, Date fi, Trajecte trajecte, Moto moto) {
        this.inici = inici;
        this.fi = fi;
        this.trajecte = trajecte;
        this.moto = moto;
        preu = calcularPreu();
        codi = crearCodi();
        initEstado();
    }

    public double getPreu() {
        return preu;
    }

    public int getCodi() {
        return codi;
    }

    public Date getDataInici() {
        return inici;
    }

    public Date getDataFi() {
        return fi;
    }

    public Trajecte getTrajecte() {
        return trajecte;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setActiva() {
        estado.setActiva(!estado.isActiva());
    }
    
    public boolean isActiva(){
        return estado.isActiva();
    }

    public void setFinaliztada() {
        this.setActiva();
        estado.setFinalitzada(true);
    }

    public int obtenirRetras() {
        return 0;
    }

    public void apuntarEndarrediment(Date retras) {

    }

    public void realitzarDescompte() {

    }

    public boolean checkMonth(int month) {
        return true;
    }

    /**
     * Calcula el preu de la reserva a partir de les dates d'inici i fi, i del
     * tajecte que es fara.
     *
     * @param inici
     * @param fi
     * @param trajecte
     * @return
     */
    private double calcularPreu() {
        return 0;
    }

    /**
     * Crea un codi aleatori de reserva a partir de les dates d'inici i fi, i
     * del tajecte que es fara.
     *
     * @param inici
     * @param fi
     * @param trajecte
     * @return
     */
    private int crearCodi() {
        return 0;
    }
    
   private void initEstado(){
       Date d = new Date();
       estado = new EstadoReserva()
   }
}
