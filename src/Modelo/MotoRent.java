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

import Vista.Interficie;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author rob3ns
 */
public class MotoRent implements Serializable {

    //private List<Local> locales;
    private Admin admin;
    private List<Cliente> clientes;
    private List<Gerente> gerentes;
    private Interficie interficie;

    public MotoRent() {
        //locales = new ArrayList();
        gerentes = new ArrayList();
        clientes = new ArrayList();
        interficie = new Interficie();
    }
    
    public static void main(String[] args) throws ParseException {
        MotoRent b = new MotoRent();
    }
}
