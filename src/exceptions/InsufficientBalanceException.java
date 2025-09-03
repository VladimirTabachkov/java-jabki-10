package exceptions;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(Double amount) {
        super("Баланс отправителя меньше, чем сумма перевода '" + amount + "'");
    }
}
