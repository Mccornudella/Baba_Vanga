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

/**
 * @author rob3ns
 */
public class Gerente extends Empleado implements Serializable{

    public Gerente() {
    }

    /**
     * Constructor solo parámetros de empleado.
     * @param correo
     * @param numeroPers 
     */
    public Gerente(String correo, int numeroPers) {
        super(correo, numeroPers);
    }

        /**
     * Constructor solo parámetros de usuario.
     * @param username
     * @param password
     * @param nombre
     * @param apellidos 
     */
    public Gerente(String username, String password, String nombre, String apellidos) {
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
    public Gerente(String correo, int numeroPers, String username, String password, String nombre, String apellidos) {
        super(correo, numeroPers, username, password, nombre, apellidos);
    }

    @Override
    public String toString() {
        return "Gerente: " + super.toString();
    }

}
