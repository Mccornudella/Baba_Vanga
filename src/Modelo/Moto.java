package modelo;

/**
 *
 * @author Jose
 */
public class Moto {

    private EspecMoto espec;
    private int ID;
    private boolean estat;
    private String matricula;

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

    /**
     * Constructor XML
     *
     * @param matricula
     * @param color
     * @param marca
     * @param model
     * @param cilindrada
     * @param ID
     * @param estat
     */
    public Moto(String matricula, String color, String marca, String model, int cilindrada, int ID, boolean estat) {
        this.matricula = matricula;
        this.espec = new EspecMoto(color, marca, model, cilindrada);
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

    public int getCilindrada() {
        return espec.getCilindrada();
    }

    public void setCilindrada(int i) {
        espec.setCilindrada(i);
    }

    public boolean getEstat() {
        return estat;
    }

    public void disponible() {
        estat = true;
    }

    public String getMatricula() {
        return matricula;
    }
    

    @Override
    public String toString() {
        return "ID: " + ID + "\n" +
               "Matricula: " + matricula + "\n" +
                espec.toString() + "\n" +
                "Estat: " + estat + "\n";
    }
    
}
