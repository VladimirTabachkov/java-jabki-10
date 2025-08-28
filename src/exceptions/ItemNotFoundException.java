package exceptions;

public class ItemNotFoundException extends Exception  {
    public ItemNotFoundException(String code) {
        super("Товар с кодом " + code + " не найден в карте товаров");
    }
}
