package vista;

/**
 * @author rob3ns
 */
public class Menu {

    private String[] opciones;
    private String titulo;
    private String tipoUsuario;

    public Menu() {
    }

    public Menu(String[] opciones) {
        this.opciones = opciones;
        this.titulo = "";
    }

    public String[] getOpciones() {
        return opciones;
    }

    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Crea un string según el tipo de usuario logueado o no logueado.
     *
     * @return String "No logueado." "Logueado como: Cliente." "Logueado como:
     * Gerente." "Logueado como: Admin."
     *
     */
    private String concatTipoUsuario() {
        String s = "\n";
        switch (tipoUsuario) {
            case "usuario":
                s += "No logueado.";
                break;
            case "cliente":
                s += "Logueado como: Cliente.";
                break;
            case "gerente":
                s += "Logueado como: Gerente.";
                break;
            case "admin":
                s += "Logueado como: Admin.";
                break;
            default:
                s += "Error.";
                break;
        }
        s += "\n";
        return s;
    }

    /**
     * Muestra por pantalla el menú para el tipo de usuario logueado. En caso de
     * no estar logueado, mostrará el menú de login.
     *
     * @return Opción seleccionada.
     */
    public int generarMenu() {
        System.out.println(titulo + this.concatTipoUsuario());
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + "- " + opciones[i]);
        }
        System.out.println((opciones.length + 1) + "- " + "Exit");

        System.out.println("\nSelecciona una opción: ");
        int opc = Interficie.selNumMenu(opciones);

        if (opc == (opciones.length + 1)) {
            System.out.println("Hasta pronto!");
            System.exit(1);
        }
        opc--; //switch trabaja de 0 a x.
        return opc;
    }
}
