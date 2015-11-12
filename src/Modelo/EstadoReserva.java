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
public class EstadoReserva {
    private boolean activa;
    private boolean finalitzada;
    private Date retraso;
    private boolean estadoMoto;
    
    /**
     * Constructor vacio.
     */
    public EstadoReserva(){
        this.activa = false;
        this.finalitzada= false;
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

    public void setRetraso(Date retraso) {
        this.retraso = retraso;
    }

    public void setEstadoMoto(boolean estadoMoto) {
        this.estadoMoto = estadoMoto;
    }

    public Date getRetraso() {
        return retraso;
    }

    public boolean isActiva() {
        return activa;
    }

    public boolean isEstadoMoto() {
        return estadoMoto;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
