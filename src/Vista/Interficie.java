
package Vista;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Classe que s'encarrega de l'interacció del programa amb el teclat i la pantalla.
 * @author Jose
 */
public class Interficie {
    
    Scanner sc;
    
    
    /**
     * Constructor de la classse.
     */
    public Interficie(){
        sc = new Scanner(System.in);
    }
    
    
   /**
    * Escriu un valor enter a la pantalla.
    * @param i 
    */
    public void escriu(int i){
        System.out.println(i);
    }
    
    /**
     * Escriu un String per pantalla.
     * @param s 
     */
    public void escriu(String s){
        System.out.println(s);
    }
    
    /**
     * Escriu un valor en coma flotant a la pantalla.
     * @param f 
     */
    public void escriu(float f){
        System.out.println(f);
    }
    
    /**
     * Escriu una data per pantalla.
     * @param d 
     */
    public void escriu (Date d){
        System.out.println(d);
    }
    
    
    /**
     * Llegeix un valor enter que s'ha entrat per teclat i el retorna.
     * @return int
     */
    public int llegeixInt(){
        int i = sc.nextInt();
        return i;
    }
    
    /**
     * Llegeix un String que s'ha entrat per teclat i el retorna.
     * @return 
     */
    public String llegeixString(){
        String s = sc.next();
        return s;
    }
    
    /**
     * Llegeix un String que despres transforma en un Date i el retorna.
     * @return 
     */
    public Date llegeixData(){
        String s = sc.next();
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        Date d = null;
        try {
            d = format.parse(s);
        } catch (ParseException ex) {
            Logger.getLogger(Interficie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
}
