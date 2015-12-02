package Modelo;

/**
 *
 * @author Jose
 */
public abstract class Penalitzacio {
    private double cost;
    
    /**
     * Constructor de la classe.
     */
    public Penalitzacio(){
        cost = 0.0;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    
            
}
