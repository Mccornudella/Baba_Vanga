package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import vista.Interficie;

/**
 * @author rob3ns
 */
public class Client extends Usuari implements Serializable {

    private String dni;
    private String correo;
    private Direccio direccion;
    private int telefono;
    private CompteBancari compteBan;
    private int faltas;
    private ArrayList<Reserva> reserves;
    private Date fechaRegistro;
    private double deuda;
    private boolean vip;

    /**
     * Constructor vacío
     */
    public Client() {
    }

    /**
     * Constructor solo parámetros usuario.
     *
     * @param username usuari en el sistema del cliente
     * @param password password del cliente
     * @param nombre nombre del cliente
     * @param apellidos apellidos del cliente
     */
    public Client(String username, String password, String nombre, String apellidos) {
        super(username, password, nombre, apellidos);
        reserves = new ArrayList();
    }

    /**
     * Constructor con parámetros de usuario y cliente.
     *
     * @param dni dni del cliente
     * @param correo correo electronico del cliente
     * @param telefono telefono del cliente
     * @param cuentaBanc cuenta bancaria del cliente
     * @param username usuari en el sistema del cliente
     * @param password password del cliente
     * @param nombre nombre del cliente
     * @param apellidos apellidos del cliente
     */
    public Client(String dni, String correo, int telefono, CompteBancari cuentaBanc, String username, String password, String nombre, String apellidos) {
        super(username, password, nombre, apellidos);
        this.dni = dni;
        this.correo = correo;
        this.telefono = telefono;
        this.compteBan = cuentaBanc;
        faltas = 0;
        fechaRegistro = new Date();
        reserves = new ArrayList();
    }

    /**
     * Constructor del parser XML
     *
     * @param dni dni del client
     * @param direccion direccion del cliente
     * @param faltas cantidad de faltas
     * @param username usuari en el sistema del cliente
     * @param password password del cliente
     * @param nombre nombre del cliente
     * @param apellidos apellidos del cliente
     * @param vip true si el cliente es vip. false si no
     */
    public Client(String dni, Direccio direccion, int faltas, String username, String password, String nombre, String apellidos, boolean vip) {
        super(username, password, nombre, apellidos);
        this.dni = dni;
        this.direccion = direccion;
        this.faltas = faltas;
        this.vip = vip;
        reserves = new ArrayList();
    }

    public String getCorreo() {
        return correo;
    }

    public CompteBancari getCuentaBanc() {
        return compteBan;
    }

    public double getDeuda() {
        return deuda;
    }

    public String getDni() {
        return dni;
    }

    public int getFaltas() {
        return faltas;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setCuentaBanc(CompteBancari cuentaBanc) {
        this.compteBan = cuentaBanc;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void addReserva(Reserva res) {
        reserves.add(res);
    }

    public boolean checkDNI(String dni) {
        return this.dni.equals(dni);
    }

    public boolean estaBloquejat() {
        return this.faltas >= 3;
    }

    /**
     * Comprueba si tiene alguna reserva no finalizada.
     *
     * @return True en caso de tener.
     */
    public boolean comprovarReserva() {
        boolean finalitzada = false;
        Iterator it = reserves.iterator();
        Reserva res = null;

        while (it.hasNext() && !finalitzada) {
            res = (Reserva) it.next();
            finalitzada = res.isFinalitzada();
        }
        return finalitzada;
    }

    public Reserva getReservaActiva() {
        Reserva r = null;
        boolean activa = false;
        Iterator it = reserves.iterator();
        Reserva res = null;
        while (it.hasNext() && !activa) {
            res = (Reserva) it.next();
            activa = res.isActiva();
        }
        r = res;
        return r;
    }

    /**
     * Comprueba que la reserva con el código introducido no esté activa.
     *
     * @param codi Código.
     * @return True en caso de no estar activa.
     */
    public boolean comprobarReservaNoActiva(String codi) {
        Iterator it = reserves.iterator();
        boolean check_codi = false;
        boolean fin = false;
        Reserva re = null;
        while (it.hasNext() && !check_codi) {
            re = (Reserva) it.next();
            check_codi = re.getCodi().equals(codi);
        }
        boolean activa = re.isActiva();
        fin = re.isFinalitzada();
        boolean comprobacio = false;
        if (check_codi && !fin) {
            if (!activa) {
                comprobacio = true;
            } else {
                Interficie.escriu("La reserva ja esta activa, no es pot tornar a entregar una moto.");
                comprobacio = false;
            }
        }
        return comprobacio;
    }

    /**
     * Comprueba que la reserva con el código introducido esté activa.
     *
     * @param codi Código.
     * @return True en caso de estar activa.
     */
    public boolean comprobarReservaActiva(String codi) {
        Iterator it = reserves.iterator();
        boolean check_codi = false;
        boolean fin = false;
        Reserva re = null;
        while (it.hasNext() && !check_codi) {
            re = (Reserva) it.next();
            check_codi = re.getCodi().equals(codi);
        }
        boolean activa = re.isActiva();
        fin = re.isFinalitzada();
        boolean comprobacio = false;
        if (check_codi && !fin) {
            if (activa) {
                comprobacio = true;
            } else {
                Interficie.escriu("La reserva no esta activa, no es pot recollir la moto.");
                comprobacio = false;
            }
        }
        return comprobacio;
    }

    public boolean isVIP() {
        return this.vip;
    }

    public ArrayList<Reserva> getReservas() {
        return this.reserves;
    }

    /**
     * Selecciona la reserva con el código pasado como parámetro.
     *
     * @param codi Código reserva.
     * @return Reserva.
     */
    public Reserva getReserva(String codi) {
        Reserva re = null;
        Iterator it = reserves.iterator();
        boolean stop = false;
        while (it.hasNext() && !stop) {
            Reserva r = (Reserva) it.next();
            if (r.getCodi().equals(codi)) {
                re = r;
            }
        }
        return re;
    }

    /**
     * Guarda información sobre los desperfectos de la moto entregada.
     *
     * @param codi Código reserva.
     */
    public void finalitzarRecollida(String codi) {
        Reserva re = getReserva(codi);
        Interficie.escriu("Te la moto algun desperfecte?(si/no): ");
        String desperfecte = Interficie.llegeixString();
        boolean continua = desperfecte.equals("si") || desperfecte.equals("no");
        while (!continua) {
            Interficie.escriu("No he entes la resposta, introdueix (si/no) si us plau.");
            desperfecte = Interficie.llegeixString();
            continua = desperfecte.equals("si") || desperfecte.equals("no");
        }
        if (desperfecte.equals("si")) {
            Moto m1 = re.getMoto();
            m1.setEstat(false);
            Double importDesperfecte = 0.0;
            Interficie.escriu("Descriu el desperfecte de la moto:");
            desperfecte = Interficie.llegeixString();
            Interficie.escriu("Introdueix el cost del desperfecte:");
            importDesperfecte = Interficie.llegeixDouble();
            deuda = deuda + importDesperfecte;
            faltas = faltas + 1;
            re.setFalta(importDesperfecte, desperfecte);

        } else {
            Moto m1 = re.getMoto();
            m1.setEstat(true);
        }

    }

    /**
     * Suma la cantidad pasada a la deuda total del cliente.
     *
     * @param cost
     */
    public void sumaDeuda(double cost) {
        deuda = deuda + cost;
    }

    /**
     * Comprueba que el local de destino coincida con el del gestor.
     *
     * @param IDGestor
     * @param codi Código reserva.
     * @return True si coincide.
     */
    public boolean comprobarLocalDestino(String IDGestor, String codi) {
        Iterator it = reserves.iterator();
        boolean check = false;
        while (it.hasNext() && !check) {
            Reserva re = (Reserva) it.next();
            if (re.getCodi().equals(codi) && re.getTrajecte().getFinal().getIDGerent().equals(IDGestor)) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Comprueba que el local de inicio coincida con el del gestor.
     *
     * @param IDGestor
     * @param codi Código reserva.
     * @return True si coincide.
     */
    public boolean comprobarLocalInicio(String IDGestor, String codi) {
        Iterator it = reserves.iterator();
        boolean check = false;
        boolean check_codi = false;
        String codiR = "";
        while (it.hasNext() && !check) {
            Reserva re = (Reserva) it.next();
            codiR = re.getCodi();
            check_codi = codi.equals(codiR);
            Trajecte t = re.getTrajecte();
            Local l = t.getInici();
            String ID = l.getIDGerent();
            boolean check_ID = false;
            check_ID = ID.equals(IDGestor);
            if (check_codi && check_ID) {
                check = true;
            }
        }
        return check;
    }

    @Override
    public String toString() {
        return "Cliente: " + super.toString();
    }

}
