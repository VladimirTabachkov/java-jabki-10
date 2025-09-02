package exceptions;

public class InvalidRatingException extends Exception {
    public InvalidRatingException(int rate) {
        super("Некорректное значение рейтинга: " + rate + " (должно быть от 1 до 5)");
    }
}
