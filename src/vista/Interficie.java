package vista;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que s'encarrega de l'interacció del programa amb el teclat i la
 * pantalla.
 *
 * @author Jose
 */
public class Interficie {

    private static String lletres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String numeros = "0123456789";
    private static Scanner sc;

    /**
     * Constructor de la classse.
     */
    public Interficie() {
        sc = new Scanner(System.in);

    }

    public Scanner getSc() {
        return sc;
    }

    /**
     * Escriu un valor enter a la pantalla.
     *
     * @param i
     */
    public static void escriu(int i) {
        System.out.println(i);
    }

    /**
     * Escriu un String per pantalla.
     *
     * @param s
     */
    public static void escriu(String s) {
        System.out.println(s);
    }

    /**
     * Escriu un valor en coma flotant a la pantalla.
     *
     * @param f
     */
    public static void escriu(float f) {
        System.out.println(f);
    }

    /**
     * Escriu una data per pantalla.
     *
     * @param d
     */
    public static void escriu(Date d) {
        System.out.println(d);
    }

    /**
     * Muestra por pantalla el array "l" que le pasemos como parámetro.
     *
     * @param l Array a mostrar.
     */
    public static void imprimirLista(ArrayList l) {
        if (!l.isEmpty()) {
            Iterator it = l.iterator();

            while (it.hasNext()) {
                Object o = it.next();
                int i = l.indexOf(o);
                System.out.println("[" + Integer.toString(i) + "] " + o.toString());
            }
        } else {
            System.out.println("No hay ningun elemento en la lista");
        }
    }

    /**
     * Llegeix un valor enter que s'ha entrat per teclat i el retorna.
     *
     * @return int
     */
    public static int llegeixInt() {
        while (!sc.hasNextInt()) {
            System.out.println("Eso no es un número.");
            sc.next();
        }
        return sc.nextInt();
    }

    public static double llegeixDouble() {
        while (!sc.hasNextDouble()) {
            System.out.println("eso no es un numero.");
            sc.next();
        }
        return sc.nextDouble();
    }

    /**
     * Llegeix un String que s'ha entrat per teclat i el retorna.
     *
     * @return
     */
    public static String llegeixString() {
        return sc.next();
    }

    /**
     * Pide un número por pantalla hasta que se introduzca uno que pertenezca al
     * array.
     *
     * @param l array
     * @return Opción seleccionada.
     */
    public static int selNumMenu(String[] l) {
        int num = llegeixInt();
        while (l.length + 1 < num || num < 0) {
            System.out.println("El número debe pertenecer a la lista(máximo " + (l.length + 1) + ").");
            num = llegeixInt();
        }
        return num;
    }

    /**
     * Pide un número por pantalla hasta que se introduzca uno que pertenezca a
     * la lista.
     *
     * @param l Lista.
     * @return Opción seleccionada.
     */
    public static int selNumLista(ArrayList l) {
        if (!l.isEmpty()) {
            int num = llegeixInt();
            while (l.size() - 1 < num || num < 0) {
                System.out.println("El número debe pertenecer a la lista(máximo " + (l.size() - 1) + ").");
                num = llegeixInt();
            }
            return num;
        }
        return -1;
    }

    /**
     * Llegeix un String que despres transforma en un Date i el retorna.
     *
     * @return Date
     */
    public static Date llegeixData() {
        boolean bona = false;
        String s = sc.next();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm/dd/MM/yyyy", Locale.ENGLISH);
        Date d = null;
        while (!bona) {
            try {
                d = format.parse(s);
                bona = true;
            } catch (ParseException ex) {
                System.out.println("Error a l'hora d'introduir la data, recorda (hh:mm/dd/MM/yyyy");
                s = sc.next();
                bona = false;
            }
        }
        return d;
    }

    /**
     * Lee un string en formato DNI. 8 números y 1 letra mayúscula.
     *
     * @return DNI
     */
    public static String llegeixDNI() {
        String s = sc.next();
        boolean correcte = false;
        while (!correcte) {
            boolean es_numero = true;
            boolean es_lletra = false;
            if (s.length() == 9) {
                int i = 0;
                while (i < 8 && es_numero) {
                    String c = s.substring(i, i + 1);
                    es_numero = numeros.contains(c);
                    i++;
                }
                String c = s.substring(8, 9);
                es_lletra = lletres.contains(c);
            }

            if (es_numero && es_lletra) {
                correcte = true;
            } else {
                System.out.println("Aquest no es un dni valid, entra un DNI amb 8 numeros i una lletra MAJUSCULA si us plau: ");
                s = sc.next();
            }
        }
        return s;
    }

    /**
     * Lee un número de cuenta bancaria.
     * @return String
     */
    public static String llegeixCB() {
        String s = sc.nextLine();
        boolean correcte = false;
        while (!correcte) {
            boolean llargada_correcte = false;
            boolean es_numero = true;
            boolean es_lletra = true;
            if (s.length() == 34) {
                llargada_correcte = true;
                int i = 2;
                while (i < 34 && es_numero) {
                    String c = s.substring(i, i + 1);
                    es_numero = numeros.contains(c);
                    i++;
                }
                String c = s.substring(0, 2);
                while (i < 2 && es_lletra) {
                    String b = s.substring(i, i + 1);
                    es_lletra = lletres.contains(b);
                }
            }

            if (es_numero && es_lletra && llargada_correcte) {
                correcte = true;
            } else {
                System.out.println("Aquest no es un Compte Bancari valid, entra un IBAN corresponent a un compte valid, si us plau: ");
                s = sc.nextLine();
            }
        }
        return s;
    }

}
