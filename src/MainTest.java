import exceptions.InvalidRatingException;
import exceptions.ItemNotFoundException;
import exceptions.LoginFailedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class MainTest {
    @Test
    void setAgeTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.setAge(-10);
        });
    }

     @Test
    void isEmptyStringTest() {
        RuntimeException ex = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> Main.isEmptyString("")
        );
        Assertions.assertEquals("Строка пуста или состоит только из пробелов", ex.getMessage());
    }

    @Test
    void getItemTestExists() {
        Main.productItems.put("169409998", "Математика");
        Main.productItems.put("169409334", "Алгебра");
        Main.productItems.put("169409672", "Геометрия");
        Assertions.assertEquals("Алгебра", Main.getItem("169409334"));
    }

    @Test
    void getItemTestNoExists() {
        Main.productItems.put("169409998", "Математика");
        Main.productItems.put("169409334", "Алгебра");
        Main.productItems.put("169409672", "Геометрия");
        RuntimeException e = Assertions.assertThrows(
                ItemNotFoundException.class,
                () -> Main.getItem("100000001")
        );
        Assertions.assertEquals("Товар с кодом " + "100000001" + " не найден в карте товаров", e.getMessage());
        }

    @Test
    void loginTest() throws LoginFailedException {
        Main.usersItems.put("Petrov", "12345678");
        Main.usersItems.put("Sidorov", "11111111");
        LoginFailedException e = Assertions.assertThrows(
                LoginFailedException.class,
                () -> Main.login("Ivanov", "000000")
        );
        Assertions.assertEquals("Недопустимое имя пользователя или пароль", e.getMessage());
    }

    @Test
    void rateProductTest() throws InvalidRatingException {
        InvalidRatingException e = Assertions.assertThrows(
                InvalidRatingException.class,
                () -> Main.rateProduct("Realme Note 50", "100")
        );
        Assertions.assertEquals("Некорректное значение рейтинга: 100 (должно быть от 1 до 5)", e.getMessage());
    }

}
