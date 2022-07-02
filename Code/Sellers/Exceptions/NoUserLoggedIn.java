package Sellers.Exceptions;

/**
 * Kein Nutzer eingeloggt
 *
 */
public class NoUserLoggedIn extends Exception {

    public NoUserLoggedIn() {
        super("Aktion nicht möglich. Kein Nutzer eingeloggt.");
    }

}
