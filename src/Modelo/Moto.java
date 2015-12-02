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
public class Moto {

    private EspecMoto espec;
    private int ID;
    private boolean estat;

    /**
     * Constructor buit.
     */
    public Moto() {
    }

    public Moto(EspecMoto espec, int ID, boolean estat) {
        this.espec = espec;
        this.ID = ID;
        this.estat = estat;
    }


    public String getColor() {
        return espec.getColor();
    }

    public void setColor(String color) {
        espec.setColor(color);
    }

    public String getMarca() {
        return espec.getMarca();
    }

    public void setMarca(String marca) {
        espec.setMarca(marca);
    }

    public String getModel() {
        return espec.getModel();
    }

    public void setModel(String model) {
        espec.setModel(model);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public int getCilindrada(){
        return espec.getCilindrada();
    }
    
    public void setCilindrada(int i){
        espec.setCilindrada(i);
    }

    public boolean getEstat() {
        return estat;
    }


    public void disponible() {
        estat = true;
    }
}
