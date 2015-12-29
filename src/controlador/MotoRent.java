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
import java.util.Calendar;
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
        String[] gerente = {"Entregar moto", "Recollir moto", "Gestionar local", "Veure estat local", "Log out"};
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
                    modificarDestino(cl);
                    break;
                case 2: //Donar-se de baixa
                    darseBaja(cl);
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
                    entregarMoto(ger);
                    break;
                case 1: //Recollir moto
                    recollirMoto(ger);
                    break;
                case 2: //Gestionar local
                    gestionarLocal(ger);
                    break;
                case 3: //Veure estat local
                    ger.veureEstatLocal();
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
                    veureLocalsSotaMinims();
                    break;
                case 1: //Veure locals sobre maxims
                    veureLocalsSobreMaxims();
                    break;
                case 2: //Veure motos locals
                    veureMotosLocals();
                    break;
                case 3: //Veure informe mensual
                    veureInformeMensual();
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
        System.out.println("Usuario: ");
        String usuario = Interficie.llegeixString();
        System.out.println("Password: ");
        String password = Interficie.llegeixString();

        int[] login = new int[2];
        Arrays.fill(login, 0);
        int pos = 0;
        boolean check = false;

        //Buscar en clientes
        pos = buscarUsuario(clientes, usuario, password);
        if (pos != -1) {
            login[0] = 1; // Tipo 1: cliente
            login[1] = pos - 1; // pos cliente en array
        }

        //Admin
        if (pos == -1 && admin != null) {
            check = admin.checkLogin(usuario, password);
            if (check) {
                login[0] = 2; // Tipo 2: admin
            }
        }

        //Buscar en gerentes
        if (pos == -1) {
            pos = buscarUsuario(gerentes, usuario, password);
            if (pos != -1) {
                login[0] = 3; // Tipo 3: gerente
                login[1] = pos - 1; // pos gerente en array
            } else {
                System.out.println("Datos incorrectos.");
            }
        }

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
        System.out.println("\nHas deslogueado con éxito.");
        inicioUsuario();
    }

    private void darseBaja(Client cl) {
        clientes.remove(cl);
        Interficie.escriu("Te has dado de baja! \n"
                + "Tus datos ya no están en nuestro sistema.");
        logout();
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
            String username = "";
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
            boolean esUsuari = true;
            while (esUsuari) {
                Interficie.escriu("Quin vols que sigui el teu Username?: ");
                username = Interficie.llegeixString();
                int i = 0;
                esUsuari = false;
                while (!esUsuari && i < clientes.size()) {
                    Client c = (Client) clientes.get(i);
                    esUsuari = c.getUsername().equals(username);
                    i++;
                }
                if (esUsuari) {
                    Interficie.escriu("Aquest usuari ja existeix!");
                }
            }
            Interficie.escriu("Quina vols que sigui la teva contrasenya?: ");
            contrasenya = Interficie.llegeixString();
            CompteBancari cb = new CompteBancari(compteBancari);

            Client c = new Client(dni, correu, telefon, cb, username, contrasenya, nom, cognoms);
            clientes.add(c);
        } else {
            Interficie.escriu("Aquest dni ja esta registrat dins del sistema");
        }

    }

    /**
     * Muestra por pantalla las motos de todos los locales.
     */
    private void veureMotosLocals() {
        for (Local l : locals) {
            Interficie.escriu("------------\nLocal " + locals.indexOf(l) + ":\n" + l.toString());
            l.veureMotos(); // imprime motos
        }
    }

    /**
     * Muestra por pantalla el informe del mes seleccionado.
     */
    private void veureInformeMensual() {
        Interficie.escriu("Introduce el mes (mm): ");
        int mes = Interficie.llegeixInt();

        if (mes < 13 && mes > 0) {
            Interficie.escriu(generarInforme(mes));
        }
    }

    /**
     * Muestra por pantalla los locales bajo mínimos (5% de su capacidad máx.).
     */
    private void veureLocalsSotaMinims() {
        for (Local l : locals) {
            int cantMin = (int) (l.getCapacitatMax() * 0.05);
            if (l.getMotos().size() <= cantMin) {
                Interficie.escriu("Capacitat 5%: " + cantMin + "\nCantidad actual: " + l.getMotos().size());
                Interficie.escriu(l.toString());
            }
        }
    }

    /**
     * Muestra por pantalla los locales sobre máximos (75% de su capacidad
     * máx.).
     */
    private void veureLocalsSobreMaxims() {
        for (Local l : locals) {
            int cantMax = (int) (l.getCapacitatMax() * 0.75f);
            if (l.getMotos().size() >= cantMax) {
                Interficie.escriu("Capacitat 75%: " + cantMax + "\nCantidad actual: " + l.getMotos().size());
                Interficie.escriu(l.toString());
            }
        }
    }

    /**
     * Genera el informe de un mes concreto para todos los clientes.
     *
     * @param mes Número del mes seleccionado.
     * @return String, informe.
     */
    private String generarInforme(int mes) {
        String info = "";
        int cantidad = 0;

        if (!clientes.isEmpty()) {
            for (Client cl : clientes) {
                ArrayList<Reserva> res = cl.getReservas();
                if (!res.isEmpty()) {
                    info += "\n................\n" + cl.toString() + "\n";
                    cantidad += cl.getReservas().size();
                    for (Reserva r : res) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(r.getDataInici());
                        if ((12 - mes) == cal.get(Calendar.MONTH)) { // Si el mes coincide, el 12 equivale a 0.
                            info += "_-_-_-_-_-_ \n";
                            info += "Local inici \n" + r.getTrajecte().getInici();
                            info += "Local fi \n" + r.getTrajecte().getFinal();
                            int horas = cal.get(Calendar.HOUR);
                            int min = cal.get(Calendar.MINUTE);
                            info += "Retras: " + String.valueOf(horas) + ":" + String.valueOf(min);
                            info += "\nEstado: " + r.getEstado().getFalta().getDescripcio() + "\n";
                        }
                    }
                    info += cantidad;
                }
            }
        }
        return info;
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

    /**
     * Reserva una moto para el cliente seleccionado. Los clientes bloqueados no
     * pueden hacer reservas.
     *
     * @param cl Cliente.
     */
    private void reservarMoto(Client cl) {
        boolean bloquejat = cl.estaBloquejat();
        boolean teReserva;
        if (!bloquejat) {
            teReserva = cl.comprovarReserva();
            if (!teReserva) {
                Interficie.escriu("Quin dia vols començar la teva reserva? (hh:mm/dd/mm/aaaa)");
                Date dataInici = Interficie.llegeixData();
                while (!comparaDataActual(dataInici)) {
                    Interficie.escriu("\nERROR! Introdueix si us plau una data posterior.\n");
                    Interficie.escriu("Quin dia vols començar la teva reserva? (hh:mm/dd/mm/aaaa)");
                    dataInici = Interficie.llegeixData();
                }
                Interficie.escriu("\nEscull el local de sortida:\n");
                Interficie.imprimirLista(locals);
                int posLocalSortida = Interficie.selNumLista(locals);
                Local localSortida = locals.get(posLocalSortida);
                Interficie.escriu("\nEscull la moto que vols fer servir:\n");
                Moto moto = localSortida.escollirMoto();
                Interficie.escriu("\nQuin dia vols acabar la teva reserva? (hh:mm/dd/mm/aaaa)");
                Date dataFinal = Interficie.llegeixData();
                while (dataFinal.before(dataInici)) {
                    Interficie.escriu("\nERROR! La data de finalitzacio ha de ser posterior a la d'inici!!\n");
                    Interficie.escriu("\nQuin dia vols acabar la teva reserva? (hh:mm/dd/mm/aaaa)");
                    dataFinal = Interficie.llegeixData();
                }
                Interficie.escriu("\nEscull el local d'arribada\n");
                Interficie.imprimirLista(locals);
                int posLocalDesti = Interficie.selNumLista(locals);
                Local localDesti = locals.get(posLocalDesti);
                boolean esVIP = cl.isVIP();
                Trajecte trajecte = new Trajecte(localSortida, localDesti);
                Reserva reserva = new Reserva(dataInici, dataFinal, trajecte, moto);
                String codiReserva = reserva.getCodi();
                if (esVIP) {
                    reserva.realitzarDescompte();
                }
                cl.sumaDeuda(reserva.getPreu());
                Interficie.escriu("Reserva confirmada: Codi de Reserva --> " + codiReserva);
                Interficie.escriu("-----------------------");
                Interficie.escriu("Data d'inici: " + dataInici.toString());
                Interficie.escriu("Local: " + localSortida.toString());
                Interficie.escriu("Moto: " + moto.toString());
                Interficie.escriu("Data de finalitzacio: " + dataFinal.toString());
                Interficie.escriu("Local: " + localDesti.toString());

                cl.addReserva(reserva);
            } else {
                Interficie.escriu("Ja tens una reserva Activa");
            }
        } else {
            Interficie.escriu("Has sigut bloquejat per haver comes mes de 3 faltes");
        }
    }

    private void modificarDestino(Client cl) {
        if (cl.comprovarReserva()) {
            Interficie.escriu("Selecciona el nuevo local de destino: ");
            Reserva r = cl.getReservaActiva();
            Local l = r.getTrajecte().getFinal();
            Local antiguo = r.getTrajecte().getFinal();
            while (l == antiguo) { //No puede poner el que tenia antes
                Interficie.imprimirLista(locals);
                l = locals.get(Interficie.selNumLista(locals));
                if (l == antiguo) {
                    Interficie.escriu("Debes seleccionar un local distinto al que tenías!");
                }
            }
            r.getTrajecte().setFinal(l);
            Interficie.escriu("Destino modificado!\n\n"
                    + l.toString());
        } else {
            Interficie.escriu("No tienes reservas activas.");
        }
    }

    /**
     * Comprueba si la fecha seleccionada es posterior a la actual del sistema.
     *
     * @param d Fecha.
     * @return True en caso de ser posterior.
     */
    private boolean comparaDataActual(Date d) {
        Date actual_date = new Date();
        return d.after(actual_date);
    }

    /**
     * El gerente pasado como parámetro guarda una moto en su local.
     *
     * @param g Gerente.
     */
    private void recollirMoto(Gerent g) {
        String codi;
        String dni;
        boolean check = false;
        boolean check2 = false;
        Interficie.escriu("Introdueix el codi de la reserva:");
        codi = Interficie.llegeixString();
        Interficie.escriu("Introdueix el DNI del client");
        dni = Interficie.llegeixDNI();
        Iterator it = clientes.iterator();
        Client cl = null;
        while (it.hasNext() && !check) {
            cl = (Client) it.next();
            check = cl.checkDNI(dni);
        }
        if (check) {
            check = cl.comprobarReservaActiva(codi);
            check2 = cl.comprobarLocalDestino(g.getID(), codi);
            if (check2) {
                if (check) {
                    Reserva re = cl.getReserva(codi);
                    cl.finalitzarRecollida(codi);
                    Date dataact = new Date();
                    int retras = (int) ((dataact.getTime() - re.getDataFi().getTime()) / (60 * 60 * 1000));
                    if (retras > 0) {
                        re.apuntarEndarrediment(retras);
                        cl.sumaDeuda(re.getCostRetras());
                        
                    }
                    re.setFinaliztada();
                    Interficie.escriu("Reserva finalitzada");
                } else {
                    Interficie.escriu("No hi ha cap reserva activa que coincideixi amb el codi introduit");
                }
            } else {
                Interficie.escriu("Aquest local no correspon amb el local desti de la reserva");
            }
        } else {
            Interficie.escriu("No hi ha cap client que coincideixi amb el DNI introduit");
        }
    }

    /**
     * El gerente pasado como parámetro entrega una moto de su local.
     *
     * @param g Gerente.
     */
    public void entregarMoto(Gerent g) {
        String codi;
        String dni;
        boolean check = false;
        boolean check2 = false;
        Interficie.escriu("Introdueix el codi de la reserva:");
        codi = Interficie.llegeixString();
        Interficie.escriu("Introdueix el DNI del client");
        dni = Interficie.llegeixDNI();
        Iterator it = clientes.iterator();
        Client cl = null;
        while (it.hasNext() && !check) {
            cl = (Client) it.next();
            check = cl.checkDNI(dni);
        }
        if (check) {
            check = cl.comprobarReservaNoActiva(codi);
            check2 = cl.comprobarLocalInicio(g.getLocal().getIDGerent(), codi);
            if (check2) {
                if (check) {
                    Reserva re = cl.getReserva(codi);
                    re.setDisponibilitatMoto(false);
                    re.setActiva();
                    cl.sumaDeuda(re.getPreu());
                    Interficie.escriu("La moto ha sigut marcada com entregada, la reserva ja esta activa.");
                } else {
                    Interficie.escriu("No hi ha cap reserva  que conicideixi amb aquest codi");
                }
            } else {
                Interficie.escriu("Aquest local no correspon amb el local d'inici de la reserva");
            }
        } else {
            Interficie.escriu("No hi ha cap client que coincideixi amb el DNI introduit.");
        }
    }

    private void gestionarLocal(Gerent ger) {
        Local locGer;
        Local locN = null;
        Local dummyLoc;
        String opc;
        int cantidad = 0;
        ArrayList nLocals = new ArrayList();
        boolean check = false;
        boolean locBuits = false;
        int num = 0;
        ArrayList nMotos = new ArrayList();

        locGer = ger.getLocal();
        Interficie.escriu("Vols moure motos del teu local a un altre? Si/No");
        opc = Interficie.llegeixString();
        Interficie.escriu("Introdueix el numero de motos que vols moure: ");
        cantidad = Interficie.llegeixInt();
        boolean traer = "No".equals(opc);

        int cont = 0;
        for (Local loc : this.locals) {
            if (traer) {
                check = loc.compDisponibilidad(cantidad);
            } else {
                check = loc.compCapacidad(cantidad);
            }
            if (check) {
                locBuits = true;
                Interficie.escriu(cont);
                Interficie.escriu(loc.toString());
            }
            cont++;
        }
        if (locBuits) {
            Interficie.escriu("Selecciona una opció de la llista: ");
            num = Interficie.llegeixInt();
            locN = this.locals.get(num);

            if (traer) {
                dummyLoc = locN;
                locN = locGer;
                locGer = dummyLoc;
                check = locGer.compDisponibilidad(cantidad);
            } else {
                check = locGer.compCapacidad(cantidad);
            }

            if (check) {
                nMotos = locGer.agafarMotos(cantidad);
                locN.afegirMotos(nMotos);
            } else {
                Interficie.escriu("El local no te suficients motos.");
            }

        }
    }
}
