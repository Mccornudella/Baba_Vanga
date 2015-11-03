/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Jose
 */
public class Trajecte {
    
    Local inici;
    Local fi;
    
    /**
     * Constructor buit.
     */
    public Trajecte(){
        
    }
    
    /**
     * Constructor simple.
     * @param inici
     * @param fi 
     */
    public Trajecte(Local inici, Local fi){
        this.inici = inici;
        this.fi = fi;
    }
    
    public Local getInici(){
        return inici;
    }
    
    public void setInici(Local inici){
        this.inici = inici;
    }
    
    public Local getFinal(){
        return fi;
    }
    
    public void setFinal(Local fi){
        this.fi = fi;
    }
}
