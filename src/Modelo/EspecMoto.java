package Modelo;

/**
 *
 * @author Jose
 */
public class EspecMoto {
    private String color;
    private String marca;
    private String model;
    private int cilindrada;

    public EspecMoto() {
    }

    public EspecMoto(String color, String marca, String model, int cilindrada) {
        this.color = color;
        this.marca = marca;
        this.model = model;
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public String getColor() {
        return color;
    }

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
}
