/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class Local {

    private ArrayList<Moto> motos;
    private Direccio direccio;
    private int capacitat_max;

    /**
     * Constructor buit.
     */
    public Local() {

    }

    /**
     * Constructor sense llista de motos.
     *
     * @param direccio
     * @param capacitat_max
     */
    public Local(Direccio direccio, int capacitat_max) {
        this.direccio = direccio;
        this.capacitat_max = capacitat_max;
        motos = new ArrayList<>(0);
    }

    /**
     * Constructor amb lista de motos.
     *
     * @param motos
     * @param direccio
     * @param capacitat_max
     */
    public Local(ArrayList<Moto> motos, Direccio direccio, int capacitat_max) {
        this.motos = motos;
        this.direccio = direccio;
        this.capacitat_max = capacitat_max;
    }

    public ArrayList getMotos() {
        return motos;
    }

    public void setMotos(ArrayList<Moto> motos) {
        this.motos = motos;
    }

    public Direccio getDireccio() {
        return direccio;
    }

    public void setDireccio(Direccio direccio) {
        this.direccio = direccio;
    }

    public int getCapacitatMax() {
        return capacitat_max;
    }

    public void setCapacitatMax(int capacitat_max) {
        this.capacitat_max = capacitat_max;
    }
    
    public Moto escollirMoto(){
        return null;
    } 
    
    public boolean compCapacidad(int cantidad){
        return true;
    }
    
    public boolean compDisponibilidad(int cantidad){
        return true;
    }
    
    public ArrayList agafarMotos(int cantidad){
        return null;
    }
    
    public void afegirMotos(int motos){
        
    }
    
    public void veureMotos(){
        
    }
    
}
