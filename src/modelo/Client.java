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

    public boolean comprobarReservaNoActiva(String codi) {
        Iterator it = reserves.iterator();
        boolean check_codi = false;
        boolean fin = false;
        Reserva re = null;
        while (it.hasNext() && !check_codi){
            re = (Reserva) it.next();
            check_codi = re.getCodi().equals(codi);
        }
        boolean activa = re.isActiva();
        fin = re.isFinalitzada();
        if (check_codi && !fin){
            if (!activa){
                return true;
            }
            else{
                Interficie.escriu("La reserva ja esta activa, no es pot tornar a entregar una moto.");
                return false;
            }
        }
        return false;
    }
    
    public boolean comprobarReservaActiva(String codi){
        Iterator it = reserves.iterator();
        boolean check_codi = false;
        boolean fin = false;
        Reserva re = null;
        while (it.hasNext() && !check_codi){
            re = (Reserva) it.next();
            check_codi = re.getCodi().equals(codi);
        }
        boolean activa = re.isActiva();
        fin = re.isFinalitzada();
        if (check_codi && !fin){
            if (activa){
                return true;
            }
            else{
                Interficie.escriu("La reserva no esta activa, no es pot recollir la moto.");
                return false;
            }
        }
        return false;
    }

    public boolean isVIP() {
        return this.vip;
    }


    public ArrayList<Reserva> getReservas() {
        return this.reserves;
    }
    
    public Reserva getReserva(String codi) {
        Reserva re = null;
        Iterator it = reserves.iterator();
        boolean stop = false;
        while (it.hasNext() && !stop){
            Reserva r = (Reserva) it.next();
            if (r.getCodi().equals(codi)){
                re = r;
            }
        }
        return re;
    }

    public void finalitzarRecollida(String codi) {
        Reserva re = getReserva(codi);
        Interficie.escriu("Te la moto algun desperfecte?(si/no): ");
        String desperfecte = Interficie.llegeixString();
        boolean continua = desperfecte.equals("si") || desperfecte.equals("no");
        while (!continua){
            Interficie.escriu("No he entes la resposta, introdueix (si/no) si us plau.");
            desperfecte = Interficie.llegeixString();
            continua = desperfecte.equals("si") || desperfecte.equals("no");
        }
        if (desperfecte.equals("si")){
            Moto m1 = re.getMoto();
            m1.setDisponible(false);
            Double importDesperfecte = 0.0;
            Interficie.escriu("Descriu el desperfecte de la moto:");
            desperfecte = Interficie.llegeixString();
            Interficie.escriu("Introdueix el cost del desperfecte:");
            importDesperfecte = Interficie.llegeixDouble();
            deuda = deuda + importDesperfecte;
            faltas = faltas + 1;
            re.setFalta(importDesperfecte,desperfecte);
            
        }
        else{
            Moto m1 = re.getMoto();
            m1.setDisponible(true);
        }

    }

    public String infoReservas(int month) {
        return null;
    }
    
    public void sumaDeuda(double cost){
        deuda = deuda + cost;
    }
    
    public boolean comprobarLocalDestino(String IDGestor){
        Iterator it = reserves.iterator();
        boolean check = false;
        while (it.hasNext() && !check){
            Reserva re = (Reserva) it.next();
            if (re.getTrajecte().getFinal().getIDGerent().equals(IDGestor)){
                check = true;
            }
        }
        return check;
    }
    
    public boolean comprobarLocalInicio(String IDGestor){
        Iterator it = reserves.iterator();
        boolean check = false;
        while (it.hasNext() && !check){
            Reserva re = (Reserva) it.next();
            if (re.getTrajecte().getInici().getIDGerent().equals(IDGestor)){
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
