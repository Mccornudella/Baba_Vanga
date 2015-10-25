/*
 * Copyright (C) 2015 NeuroCore <http://rob3ns.github.io/NeuroCore/>
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package Modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author rob3ns
 */
public class Cliente extends Usuario implements Serializable{

    private String Dni;
    private String correo;
    private int telefono;
    private int cuentaBanc;
    private int faltas;
    //private Reserva reserva;
    private Date fechaRegistro;
    private int deuda;

    /**
     * Constructor vacío
     */
    public Cliente() {
    }

    /**
     * Constructor solo parámetros usuario.
     * @param username
     * @param password
     * @param nombre
     * @param apellidos 
     */
    public Cliente(String username, String password, String nombre, String apellidos) {
        super(username, password, nombre, apellidos);
    }

    /**
     * Constructor con parámetros de usuario y cliente.
     * @param Dni
     * @param correo
     * @param telefono
     * @param cuentaBanc
     * @param faltas
     * @param fechaRegistro
     * @param deuda
     * @param username
     * @param password
     * @param nombre
     * @param apellidos 
     */
    public Cliente(String Dni, String correo, int telefono, int cuentaBanc, int faltas, Date fechaRegistro, int deuda, String username, String password, String nombre, String apellidos/*, Reserva reserva*/) {
        super(username, password, nombre, apellidos);
        this.Dni = Dni;
        this.correo = correo;
        this.telefono = telefono;
        this.cuentaBanc = cuentaBanc;
        this.faltas = faltas;
        this.fechaRegistro = fechaRegistro;
        this.deuda = deuda;
        //this.reserva = reserva;
    }

    public String getCorreo() {
        return correo;
    }

    public int getCuentaBanc() {
        return cuentaBanc;
    }

    public int getDeuda() {
        return deuda;
    }

    public String getDni() {
        return Dni;
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

    public void setCuentaBanc(int cuentaBanc) {
        this.cuentaBanc = cuentaBanc;
    }

    public void setDeuda(int deuda) {
        this.deuda = deuda;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
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

    @Override
    public String toString() {
        return "Cliente: " + super.toString();
    }

}
