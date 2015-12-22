
package controlador;
import controlador.parser.MotoRentDataManager;
import modelo.Admin;
import modelo.Client;
import modelo.CompteBancari;
import modelo.Gerent;
import modelo.Local;
import modelo.Usuari;
import vista.Interficie;
import vista.Menu;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import modelo.Moto;
import modelo.Reserva;
import modelo.Trajecte;

/**
 * @author rob3ns
 */
public class MotoRent implements Serializable {

    private ArrayList<Local> locals;
    private Admin admin;
    private List<Client> clientes;
    private List<Gerent> gerentes;
    private ArrayList<String[]> opciones;
    private Menu menu;
    private MotoRentDataManager dataMgr;

    public MotoRent() {
        locals = new ArrayList();
        gerentes = new ArrayList();
        clientes = new ArrayList();
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
                    registrarse();
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
                    reservarMoto(cl);
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
                    veureMotosLocals();
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
        String usuario = Interficie.llegeixString();
        System.out.println("Password: ");
        String password = Interficie.llegeixString();

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

    private void registrarse() {
        Interficie.escriu("Quin es el teu DNI?: ");
        String dni = Interficie.llegeixDNI();

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

            Interficie.escriu("Quin es el teu Nom ?: ");
            nom = Interficie.llegeixString();
            Interficie.escriu("Quins son els teus cognoms?: ");
            cognoms = Interficie.llegeixString();
            Interficie.escriu("Quin es el teu telefon?: ");
            telefon = Interficie.llegeixInt();
            Interficie.escriu("Quin es el teu correu electronic?: ");
            correu = Interficie.llegeixString();
            Interficie.escriu("Quin es el teu compteBancari?: ");
            compteBancari = Interficie.llegeixCB();
            Interficie.escriu("Quin vols que sigui el teu Username?: ");
            username = Interficie.llegeixString();
            Interficie.escriu("Quina vols que sigui la teva contrasenya?: ");
            contrasenya = Interficie.llegeixString();
            CompteBancari cb = new CompteBancari(compteBancari);

            Client c = new Client(dni, correu, telefon, cb, username, contrasenya, nom, cognoms);
            clientes.add(c);
        } else {
            Interficie.escriu("Aquest dni ja esta registrat dins del sistema");
        }

    }

    public void veureMotosLocals() {
        for (Local l : locals) {
            Interficie.escriu("------------\nLocal " + locals.indexOf(l) + ":\n" + l.toString());
        }
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
        return locals;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    
    private void reservarMoto(Client cl){
        boolean bloquejat = cl.estaBloquejat();
        boolean teReserva;
        if (!bloquejat){
            teReserva = cl.comprovarReserva();
            if (!teReserva){
                Interficie.escriu("Quin dia vols començar la teva reserva? (hh:mm/dd/mm/aaaa)");
                Date dataInici = Interficie.llegeixData();
                Interficie.escriu("Escull el local de sortida");
                Interficie.imprimirLista(locals);
                int posLocalSortida = Interficie.selNumLista(locals);
                Local localSortida = locals.get(posLocalSortida);
                Interficie.escriu("Escull la moto que vosl fer servir");
                Moto moto = localSortida.escollirMoto();
                Interficie.escriu("Quin dia vols acabar la teva reserva? (hh:mm/dd/mm/aaaa)");
                Date dataFinal = Interficie.llegeixData();
                Interficie.escriu("Escull el local d'arribada");
                Interficie.imprimirLista(locals);
                int posLocalDesti = Interficie.selNumLista(locals);
                Local localDesti = locals.get(posLocalDesti);
                boolean esVIP = cl.isVIP();
                Trajecte trajecte = new Trajecte(localSortida,localDesti);
                Reserva reserva = new Reserva(dataInici,dataFinal,trajecte,moto);
                int codiReserva = reserva.getCodi();
                if (esVIP){
                    reserva.realitzarDescompte();
                }
                Interficie.escriu(codiReserva);
                cl.addReserva(reserva);
            }
            else{
                Interficie.escriu("Ja tens una reserva Activa");
            }
        }
        else{
            Interficie.escriu("Has sigut bloquejat per haver comes mes de 3 faltes");
        }   
    }
}