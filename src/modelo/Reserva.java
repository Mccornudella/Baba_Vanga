package modelo;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author Jose
 */
public class Reserva {

    private final int precioHora = 1;
    private final int precioDia = 15;

    private double preu;
    private String codi;
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
        crearCodi();
        initEstado();
    }

    public double getPreu() {
        return preu;
    }

    public String getCodi() {
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
    
    public boolean isFinalitzada(){
        return estado.isFinalitzada();
    }

    public void apuntarEndarrediment(int retras) {
        estado.apuntarEndarrediment(retras);
    }
   
    public double getCostRetras(){
        return estado.getCostRetras();
    }

    public void realitzarDescompte() {

    }

    public boolean checkMonth(int month) {
        return true;
    }
    
    public void setDisponibilitatMoto(boolean estat){
        moto.setDisponible(estat);
    }

    /**
     * Calcula el preu de la reserva a partir de les dates d'inici i fi.
     *
     * @param inici
     * @param fi
     * @param trajecte
     * @return
     */
    private double calcularPreu() {
        int hores = (int) ((fi.getTime() - inici.getTime()) /(60 * 60 * 1000));
        double cost = 0.0;
        int dies = 0;
        if (hores < 24){
            cost = hores;
            return cost;
        }
        else{
            while (hores >= 24){
                hores = hores - 24;
                dies = dies + 1;
            }
            return hores + (dies*15);
        }
    }

    /**
     * Crea un codi aleatori de reserva
     *
     * @return
     */
    private void crearCodi() {
        Random r = new Random();
        int cd = 0;
        boolean stop;
        stop = false;
        while(!stop){
            cd = r.nextInt(10000000); 
            stop = cd > 1000000;
        }
        String scodi = Integer.toString(cd);
        String concat = scodi.concat(moto.getMatricula());
        codi = concat;
    }
    
   private void initEstado(){
       Date d = new Date();
<<<<<<< Updated upstream
       estado = new EstadoReserva(!d.after(fi),d.after(fi));
   }

    public EstadoReserva getEstado() {
        return estado;
    }
=======
       estado = new EstadoReserva(false,d.after(fi));
       
   }
   
   public void setFalta(Double importe, String desperfecte){
       estado.setFalta(importe,desperfecte);
   }
>>>>>>> Stashed changes
}
