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

    String color;
    String marca;
    String model;
    int ID;
    String estat;

    /**
     * Constructor buit.
     */
    public Moto() {
    }

    /**
     * Constructor simple.
     *
     * @param color
     * @param marca
     * @param model
     * @param ID
     * @param estat
     */
    public Moto(String color, String marca, String model, int ID, String estat) {
        this.color = color;
        this.marca = marca;
        this.model = model;
        this.ID = ID;
        this.estat = estat;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }
}
