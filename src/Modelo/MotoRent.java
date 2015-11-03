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
import Vista.Menu;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rob3ns
 */
public class MotoRent implements Serializable {

    private List<Local> locales;
    private Admin admin;
    private List<Cliente> clientes;
    private List<Gerente> gerentes;
    private Interficie interficie;
    private ArrayList<String[]> opciones;
    private Menu menu;

    public MotoRent() {
        locales = new ArrayList();
        gerentes = new ArrayList();
        clientes = new ArrayList();
        interficie = new Interficie();
        opciones = new ArrayList(); // [0][x] - usuario, [1][x] - cliente, [2][x] - gerente, [3][x] - admin
        inicializarOpciones();
        menu = new Menu(opciones.get(0)); // usuario
    }
    
    public static void main(String[] args) throws ParseException {
        MotoRent mrent = new MotoRent();
        
        int opc = 0;
        int[] log = {0, 0};
        while (opc != -1 && log[0] == 0) { // usuario primero
            opc = mrent.menu.generarMenu();
            switch(opc) {
                case 0: // log in
                    log = mrent.login();
                    break;
                case 1:  // registrar
                    System.out.println("Aún no creado.");
                    break;
                default:
                    break;
            }
        }
        
        if (opc == -1)
            return;
        
        //Cambio opciones el menu y busco objeto segun del tipo que sea el login
        mrent.menu.setOpciones(mrent.opciones.get(log[0]));
        Cliente cl;
        Gerente ger;
        opc = 0;
        
        //Vamos al menu que toque
        switch (log[0]) {
            case 1:
                cl = mrent.clientes.get(log[1]);
                mrent.menuCliente(cl);
                break;
            case 2:
                mrent.menuAdmin();
                break;
            case 3:
                ger = mrent.gerentes.get(log[1]);
                mrent.menuGerente(ger);
                break;
            default:
                break;
        }
    }
    
    private void menuCliente(Cliente cl) {
        int opc = 0;
        while (opc != -1) {
            opc = menu.generarMenu();
            switch(opc) {
                case 1: //Reserv
                    break;
                case 2: //Modificar dest
                    break;
                case 3: //Baixa
                    break;
                case 4: // Log out
                    break;
                default: // Exit
                    break;
            }
        }
    }
    
    private void menuGerente(Gerente ger) {
        int opc = 0;
        while (opc != -1) {
            opc = menu.generarMenu();
            switch(opc) {
                case 1: //Compr res
                    break;
                case 2: //Gest local
                    break;
                case 3: //Log out
                    break;
                default: // Exit
                    break;
            }
        }
    }
    
    private void menuAdmin() {
        int opc = 0;
        while (opc != -1) {
            opc = menu.generarMenu();
            switch(opc) {
                case 1: //Afegir local
                    break;
                case 2: //Afegir moto
                    break;
                case 3: //Eliminar gerent
                    break;
                case 4: //Informe mensual
                    break;
                case 5: //Log out
                    break;
                default: // Exit
                    break;
            }
        }
    }
    
    private void inicializarOpciones() {
        String[] usuario = {"Log in", "Registrar"};
        String[] cliente = {"Reservar moto", "Modificar desti", "Donar-se de baixa", "Log out"};
        String[] gerente = {"Comprovar reserva", "Gestionar local", "Log out"};
        String[] admn = {"Afegir local", "Afegir moto", "Eliminar gerent", "Veure informe mensual", "Log out"};
        
        opciones.add(usuario);
        opciones.add(cliente);
        opciones.add(gerente);
        opciones.add(admn);
    }
    
    public int[] login(){
        System.out.println("Usuario: ");
        String usuario = interficie.llegeixString();
        System.out.println("Password: ");
        String password = interficie.llegeixString();
        int[] login = {0, 0};
        boolean corr = false;
        int i = 0;
        
        //Buscar en clientes
        if (!clientes.isEmpty()) {
            while (i < clientes.size() && !corr) {
                Cliente cl = clientes.get(i);
                corr = cl.checkPass(usuario, password);
                i++;
            }     
            if (corr) {
                login[0] = 1;
                login[1] = i;
                return login;
            }
        }
        
        //Admin
        if (admin != null) {
            if (admin.checkPass(usuario, password)) {
                login[0] = 2;
                return login;
            }
        }
        //Buscar en gerentes
        if (!gerentes.isEmpty()) {
            corr = false;
            i = 0;
            while (i < gerentes.size() && !corr) {
                Gerente gr = gerentes.get(i);
                corr = gr.checkPass(usuario, password);
                i++;
            }   
            if (corr) {
                login[0] = 3;
                login[1] = i;
                return login;
            }
        }
        
        System.out.println("Datos incorrectos.");
        return login;
    }
}
