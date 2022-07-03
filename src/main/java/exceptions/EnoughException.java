package exceptions;

/**
 * @author elouhichi
 * @created 29/06/2022 - 17:41
 * @project KataBankAccount
 */
public class EnoughException extends Exception{

    public EnoughException() {
        super("not enough money to withdraw!");
    }
}
