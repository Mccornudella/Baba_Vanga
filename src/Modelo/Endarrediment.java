package Modelo;

import java.util.Date;

/**
 *
 * @author Jose
 */
public class Endarrediment extends Penalitzacio{
    private Date retras;

    public Endarrediment(Date retras) {
        this.retras = retras;
    }

    public void setRetras(Date retras) {
        this.retras = retras;
    }

    public Date getRetras() {
        return retras;
    }
    
    public void calcularCost(){
         double preu = retras.getHours() * 2;
         super.setCost(preu);
        
    }
    
}
