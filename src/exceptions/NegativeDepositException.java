package exceptions;

public class NegativeDepositException extends Exception {
    public NegativeDepositException(double amount) {
        super("На депозит нельзя клась отрицательное значение (" + amount + ")");
    }
}
