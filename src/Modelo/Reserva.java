/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Jose
 */
public class Reserva {
    double preu;
    int codi;
    Date inici;
    Date fi;
    Trajecte trajecte;
    Moto moto;
    
    /**
     * Constructor buit.
     */
    public Reserva(){
     
    }
    
    /**
     * Constructor simple.
     * @param inici
     * @param fi
     * @param trajecte
     * @param moto 
     */
    public Reserva(Date inici, Date fi, Trajecte trajecte, Moto moto){
        this.inici = inici;
        this.fi = fi;
        this.trajecte = trajecte;
        this.moto = moto;
        preu = calcularPreu(inici,fi,trajecte);
        codi = crearCodi(inici,fi,trajecte);
    }
    
    public double getPreu(){
        return preu;
    }
    
    public int getCodi(){
        return codi;
    }
    
    public Date getDataInici(){
        return inici;
    }
    
    public Date getDataFi(){
        return fi;
    }
    
    public Trajecte getTrajecte(){
        return trajecte;
    }
    
    public Moto getMoto(){
        return moto;
    }
    
    /**
     * Calcula el preu de la reserva a partir de les dates d'inici i fi, i del tajecte que es fara.
     * @param inici
     * @param fi
     * @param trajecte
     * @return 
     */
    public double calcularPreu(Date inici, Date fi, Trajecte trajecte){
        return 0;
    }
    
    /**
     * Crea un codi aleatori de reserva a partir de les dates d'inici i fi, i del tajecte que es fara.
     * @param inici
     * @param fi
     * @param trajecte
     * @return 
     */
    public int crearCodi(Date inici, Date fi, Trajecte trajecte){
        return 0;
    }
}
