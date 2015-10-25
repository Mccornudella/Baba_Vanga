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

/**
 * @author rob3ns
 */

public class Empleado extends Usuario{

    private String correo;
    private int numeroPers;

    /**
     * Constructor simple, solo parámetros de empleado.
     * @param correo
     * @param numeroPers Teléfono.
     */
    public Empleado(String correo, int numeroPers) {
        this.correo = correo;
        this.numeroPers = numeroPers;
    }
    
    /**
     * Constructor solo parámetros de usuario.
     * @param username
     * @param password
     * @param nombre
     * @param apellidos 
     */
    public Empleado(String username, String password, String nombre, String apellidos) {
        super(username, password, nombre, apellidos);
    }

    /**
     * Constructor con parámetros de usuario y empleado.
     * @param correo
     * @param numeroPers
     * @param username
     * @param password
     * @param nombre
     * @param apellidos 
     */
    public Empleado(String correo, int numeroPers, String username, String password, String nombre, String apellidos) {
        super(username, password, nombre, apellidos);
        this.correo = correo;
        this.numeroPers = numeroPers;
    }
    
    /**
     * Constructor vacío.
     */
    public Empleado() {
        super(null, null, null, null);
    }     

    public String getCorreo() {
        return correo;
    }

    public int getNumeroPers() {
        return numeroPers;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNumeroPers(int numeroPers) {
        this.numeroPers = numeroPers;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
