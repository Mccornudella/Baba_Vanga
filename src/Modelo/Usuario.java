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
public class Usuario implements Serializable{

    private String username;
    private String password;
    private String nombre;
    private String apellidos;

    /**
     * Constructor simple.
     * @param username
     * @param password
     * @param nombre
     * @param apellidos Ambos apellidos separados por un espacio.
     */
    public Usuario(String username, String password, String nombre, String apellidos) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
        
    /**
     * Constructor vacío.
     */
    public Usuario() {
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean checkPass(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
