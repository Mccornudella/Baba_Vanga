package modelo;

/**
 *
 * @author Jose
 */
public class CompteBancari {

    private String pais;
    private String control_iban;
    private String entitat;
    private String oficina;
    private String control_compte;
    private String num_compte;

    public CompteBancari(String compte) {
        pais = compte.substring(0, 2);
        control_iban = compte.substring(2, 4);
        entitat = compte.substring(4, 8);
        oficina = compte.substring(8, 12);
        control_compte = compte.substring(12, 14);
        num_compte = compte.substring(14, compte.length());
    }

    @Override
    public String toString() {
        return ("Pais: " + pais
                + " ControlIBAN: " + control_iban
                + " Entitat: " + entitat
                + " Oficina: " + oficina
                + " ControlCompte: " + control_compte
                + " Numero de Compte: " + num_compte);
    }
}
