package Controlador;

import Modelo.Admin;
import Modelo.Client;
import Modelo.CompteBancari;
import Modelo.Gerent;
import Modelo.Local;
import Modelo.Usuari;
import Vista.Interficie;
import Vista.Menu;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author rob3ns
 */
public class MotoRent implements Serializable {

    private List<Local> locales;
    private Admin admin;
    private List<Client> clientes;
    private List<Gerent> gerentes;
    private Interficie interficie;
    private ArrayList<String[]> opciones;
    private Menu menu;
    private MotoRentDataManager dataMgr;

    public MotoRent() {
        locales = new ArrayList();
        gerentes = new ArrayList();
        clientes = new ArrayList();
        interficie = new Interficie();
        dataMgr = new MotoRentDataManager();

        //Cargar datos
        iniciarManager();

        // Menu
        opciones = new ArrayList(); // [0][x] - usuario, [1][x] - cliente, [2][x] - gerente, [3][x] - admin
        inicializarOpciones();
        menu = new Menu(opciones.get(0)); // usuario
        menu.setTitulo("\n---------\nMOTO RENT\n---------");
        menu.setTipoUsuario("usuario");
    }

    public static void main(String[] args) throws ParseException {
        MotoRent mrent = new MotoRent();
        mrent.inicioUsuario();
    }

    private void iniciarManager() {
        dataMgr.iniciar(this);
        dataMgr.obtenirDades("data/MotoRent.xml");
    }

    private void inicializarOpciones() {
        String[] usuario = {"Log in", "Registrar"};
        String[] cliente = {"Reservar moto", "Modificar desti", "Donar-se de baixa", "Log out"};
        String[] gerente = {"Entregar moto", "Recollir moto", "Gestionar local", "Veure estat d'un local", "Log out"};
        String[] admn = {"Veure locals sota minims", "Veure locals sobre maxims", "Veure motos locals", "Veure informe mensual", "Log out"};

        opciones.add(usuario);
        opciones.add(cliente);
        opciones.add(admn);
        opciones.add(gerente);
    }

    private void inicioUsuario() {
        int opc = 0;
        int[] log = new int[2]; // {tipo usuario, posicion array}
        Arrays.fill(log, 0);

        while (opc != -1 && log[0] == 0) { // Menu usuario
            opc = menu.generarMenu();
            switch (opc) {
                case 0: // Log in
                    log = login();
                    break;
                case 1:  // Registrar
                    Registrarse();
                    break;
                default: // Exit
                    break;
            }
        }

        //Cambio opciones el menu y busco objeto segun del tipo que sea el login
        menu.setOpciones(opciones.get(log[0]));
        opc = 0;

        //Vamos al menu que toque
        switch (log[0]) {
            case 1: // Client
                Client cl = clientes.get(log[1]);
                menu.setTipoUsuario("cliente");
                menuCliente(cl);
                break;
            case 2: // Admin
                menu.setTipoUsuario("admin");
                menuAdmin();
                break;
            case 3: // Gerent
                Gerent ger = gerentes.get(log[1]);
                menu.setTipoUsuario("gerente");
                menuGerente(ger);
                break;
            default:
                break;
        }
    }

    private void menuCliente(Client cl) {
        int opc = 0;
        while (opc < 3) {
            opc = menu.generarMenu();
            switch (opc) {
                case 0: //Reservar moto
                    break;
                case 1: //Modificar desti
                    break;
                case 2: //Donar-se de baixa
                    break;
                case 3: //Log out
                    logout();
                    break;
                default: //Exit
                    break;
            }
        }
    }

    private void menuGerente(Gerent ger) {
        int opc = 0;
        while (opc < 4) {
            opc = menu.generarMenu();
            switch (opc) {
                case 0: //Entregar moto
                    break;
                case 1: //Recollir moto
                    break;
                case 2: //Gestionar local
                    break;
                case 3: //Veure estat d'un local
                    break;
                case 4: //Log out
                    logout();
                    break;
                default: //Exit
                    break;
            }
        }
    }

    private void menuAdmin() {
        int opc = 0;
        while (opc < 4) {
            opc = menu.generarMenu();
            switch (opc) {
                case 0: //Veure locals sota minims
                    break;
                case 1: //Veure locals sobre maxims
                    break;
                case 2: //Veure motos locals
                    break;
                case 3: //Veure informe mensual
                    break;
                case 4: //Log out
                    logout();
                    break;
                default: //Exit
                    break;
            }
        }
    }

    private int[] login() {
        // Usuarios para probar login
        Client c = new Client("ssd", "asd", 888, new CompteBancari("4475869584758493847584938475849384"), "usuari1", "usuari1", "as", "d");
        clientes.add(c);
        Gerent g = new Gerent("usuari2", "usuari2", "as", "d");
        gerentes.add(g);

        System.out.println("Usuario: ");
        String usuario = interficie.llegeixString();
        System.out.println("Password: ");
        String password = interficie.llegeixString();

        int[] login = new int[2];
        Arrays.fill(login, 0);
        int pos = 0;

        //Buscar en clientes
        pos = buscarUsuario(clientes, usuario, password);
        if (pos != -1) {
            login[0] = 1; // Tipo 1: cliente
            login[1] = pos - 1; // pos cliente en array
            return login;
        }

        //Admin
        if (admin != null) {
            if (admin.checkLogin(usuario, password)) {
                login[0] = 2; // Tipo 2: admin
                return login;
            }
        }

        //Buscar en gerentes
        pos = buscarUsuario(gerentes, usuario, password);
        if (pos != -1) {
            login[0] = 3; // Tipo 3: gerente
            login[1] = pos - 1; // pos gerente en array
            return login;
        }

        System.out.println("Datos incorrectos.");
        return login;
    }

    /**
     * Busca un usuario con el nombre y password especificados.
     *
     * @param l Lista de usuarios (tipo: cliente o gerente).
     * @param usuario Login
     * @param pssw Password
     * @return Si lo encuentra, posicion en el array. No encuentra, -1.
     */
    private int buscarUsuario(List l, String usuario, String pssw) {
        boolean corr = false;
        int i = 0;
        if (!l.isEmpty()) {
            while (i < l.size() && !corr) {
                Usuari u = (Usuari) l.get(i);
                corr = u.checkLogin(usuario, pssw);
                i++;
            }
        }
        if (!corr) {
            i = -1;
        }

        return i;
    }

    private void logout() {
        menu.setOpciones(opciones.get(0)); // usuario
        menu.setTipoUsuario("usuario");
        System.out.println("\nHas deslogueado con exito.");
        inicioUsuario();
    }

    private void Registrarse() {
        interficie.escriu("Quin es el teu DNI?: ");
        String dni = interficie.llegeixDNI();

        Iterator it = clientes.iterator();
        boolean esCliente = false;
        while (it.hasNext() && !esCliente) {
            Client c = (Client) it.next();
            esCliente = c.checkDNI(dni);
        }

        if (!esCliente) {
            String nom;
            String cognoms;
            int telefon;
            String correu;
            String compteBancari;
            String username;
            String contrasenya;

            interficie.escriu("Quin es el teu Nom ?: ");
            nom = interficie.llegeixString();
            interficie.escriu("Quins son els teus cognoms?: ");
            cognoms = interficie.llegeixString();
            interficie.escriu("Quin es el teu telefon?: ");
            telefon = interficie.llegeixInt();
            interficie.escriu("Quin es el teu correu electronic?: ");
            correu = interficie.llegeixString();
            interficie.escriu("Quin es el teu compteBancari?: ");
            compteBancari = interficie.llegeixCB();
            interficie.escriu("Quin vols que sigui el teu Username?: ");
            username = interficie.llegeixString();
            interficie.escriu("Quina vols que sigui la teva contrasenya?: ");
            contrasenya = interficie.llegeixString();
            CompteBancari cb = new CompteBancari(compteBancari);

            Client c = new Client(dni, correu, telefon, cb, username, contrasenya, nom, cognoms);
            clientes.add(c);
        } else {
            interficie.escriu("Aquest dni ja esta registrat dins del sistema");
        }

    }

    public void veureMotosLocals() {

    }

    public void veureInforme(int month) {

    }

    private String generarInforme(int month) {
        return null;
    }

    public List<Client> getClientes() {
        return clientes;
    }

    public List<Gerent> getGerentes() {
        return gerentes;
    }

    public List<Local> getLocales() {
        return locales;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
