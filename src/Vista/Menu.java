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
package Vista;

/**
 * @author rob3ns
 */
public class Menu {

    private String[] opciones;
    private String titulo;
    private String tipoUsuario;
    private Interficie teclado;

    public Menu() {
    }

    public Menu(String[] opciones) {
        this.opciones = opciones;
        this.teclado = new Interficie();
        this.titulo = "";
    }

    public String[] getOpciones() {
        return opciones;
    }

    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    private String concatTipoUsuario() {
        String s = "\n";
        switch (tipoUsuario) {
            case "usuario":
                s += "No logueado.";
                break;
            case "cliente":
                s += "Logueado como: Cliente.";
                break;
            case "gerente":
                s += "Logueado como: Gerente.";
                break;
            case "admin":
                s += "Logueado como: Admin.";
                break;
            default:
                s+= "Error.";
                break;
        }
        s+= "\n";
        return s;
    }

    /**
     * Aquí iría lo de los hilos para el estado del local en gerente
     */
    public int generarMenu() {
        System.out.println(titulo + this.concatTipoUsuario());
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + "- " + opciones[i]);
        }
        System.out.println((opciones.length + 1) + "- " + "Exit");

        System.out.println("\nSelecciona una opción: ");
        int opc = teclado.selNumMenu(opciones);

        if (opc == (opciones.length + 1)) {
            System.out.println("Hasta pronto!");
            System.exit(1);
        }
        opc--; //switch trabaja de 0 a x.
        return opc;
    }
}
