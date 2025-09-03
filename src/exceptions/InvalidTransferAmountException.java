package exceptions;

public class InvalidTransferAmountException extends Exception {
    public InvalidTransferAmountException(Double amount) {
        super("Недопустимое значение суммы перевода '" + amount + "'");
    }
}
