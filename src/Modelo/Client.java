package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author rob3ns
 */
public class Client extends Usuari implements Serializable{

    private String dni;
    private String correo;
    private int telefono;
    private CompteBancari compteBan;
    private int faltas;
    private ArrayList<Reserva> reserves;
    private Date fechaRegistro;
    private int deuda;
    private boolean vip;

    //id, nom, usuari, password, vip*, renovacio?, faltes, dni, adreca
    /**
     * Constructor vacío
     */
    public Client() {
    }

    /**
     * Constructor solo parámetros usuario.
     * @param username
     * @param password
     * @param nombre
     * @param apellidos 
     */
    public Client(String username, String password, String nombre, String apellidos) {
        super(username, password, nombre, apellidos);
    }

    /**
     * Constructor con parámetros de usuario y cliente.
     * @param dni
     * @param correo
     * @param telefono
     * @param cuentaBanc
     * @param username
     * @param password
     * @param nombre
     * @param apellidos 
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
     * @param dni
     * @param correo
     * @param faltas
     * @param username
     * @param password
     * @param nombre
     * @param apellidos 
     */
    public Client(String dni, String correo, int faltas, String username, String password, String nombre, String apellidos, boolean vip) {
        super(username, password, nombre, apellidos);
        this.dni = dni;
        this.correo = correo;
        this.faltas = faltas;
        this.vip = vip;
    }

    
    public String getCorreo() {
        return correo;
    }

    public CompteBancari getCuentaBanc() {
        return compteBan;
    }

    public int getDeuda() {
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

    public void setDeuda(int deuda) {
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
    
    public void addReserva(Reserva res){
        reserves.add(res);
    }

    public boolean checkDNI(String dni) {
        return this.dni.equals(dni);
    }
    
    public boolean estaBloquejat(){
        return this.faltas >= 3;
    }
    
    public boolean comprovarReserva(Reserva res){
        return true;
    }
    
    public boolean comprobarCodigo(int codi){
        return true;
    }
    
    public boolean isVIP(){
        return this.vip;
    }
    
    public Reserva getReserva(){
        return null;
    }
    
    public void finalitzarRecollida(){
        
    }
    
    public String infoReservas(int month){
        return null;
    }
    
    @Override
    public String toString() {
        return "Cliente: " + super.toString();
    }

}
