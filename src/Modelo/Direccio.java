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
public class Direccio {
    
    private String pais;
    private String ciutat;
    private String carrer;
    private int numero;
    private int cp;
    
    /**
     * Constructor buit.
     */
    public Direccio(){
    }
    
    /**
     * Constructor simple.
     * @param pais
     * @param ciutat
     * @param carrer
     * @param numero
     * @param cp 
     */
    public Direccio(String pais, String ciutat, String carrer, int numero, int cp){
        this.pais = pais;
        this.ciutat = ciutat;
        this.carrer = carrer;
        this.numero = numero;
        this.cp = cp;
    }
    
    public String getPais(){
        return pais;
    }
    
    public void setPais(String pais){
        this.pais = pais;
    }
    
    public String getCiutat(){
        return ciutat;
    }
    
    public void setCiutat(String ciutat){
        this.ciutat = ciutat;
    }
    
    public String getCarrer(){
        return carrer;
    }
    
    public void setCarrer(String carrer){
        this.carrer = carrer;
    }
    
    public int getNumero(){
        return numero;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public int getCP(){
        return cp;
    }
    
    public void setCP(int cp){
        this.cp = cp;
    }
}
