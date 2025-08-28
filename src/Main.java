import exceptions.InsufficientBalanceException;
import exceptions.InvalidRatingException;
import exceptions.InvalidTransferAmountException;
import exceptions.ItemNotFoundException;
import exceptions.LoginFailedException;
import exceptions.NegativeDepositException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    // список товаров (для задания 6)
    static HashMap<String, String> productItems = new HashMap<>();

    // список пользователей системы (для задания 8)
    static HashMap<String, String> usersItems = new HashMap<>();

    // список банковских счетов (для задания 9)
    static HashMap<String, Double> accountsItems = new HashMap<>();

    // список рейтингов товаров (для задания 10)
    static HashMap<String, Integer> rateProductsItems = new HashMap<>();

    public static void main(String[] args) {

        /**
         * 1. безопасное деление
         * напишите метод safedivide(int a, int b), который возвращает a / b.
         * если b == 0, перехватите исключение и выведите сообщение: "деление на ноль запрещено".
         * прикольное задание. заодно узнал, что такое "infinity" в java :))
         */
        System.out.println("Результат деления " + 21 + " на " + 3 + ": " + safeDivide(21, 3));
        System.out.println("Результат деления " + 10 + " на " + 0 + ": " + safeDivide(10, 0));

        /**
         * 2. Проверка строки
         * Напишите метод, который принимает строку и выбрасывает IllegalArgumentException,
         * если строка пуста или состоит только из пробелов.
         */
        try {
            isEmptyString(" QQQQ ");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
          isEmptyString("  ");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /**
         * 3. Преобразование строки в число
         * Дан список строк List.of("10", "abc", "5").
         * Преобразуйте его в список чисел, перехватывая NumberFormatException.
         * Ошибки не должны останавливать выполнение.
         */
        System.out.printf("Преобразовано %s элементов\n", toIntegerList(new ArrayList<>(List.of("10", "abc", "5"))).size());

        /**
         4. Простая валидация возраста
         Метод setAge(int age) должен выбрасывать IllegalArgumentException,
         если возраст меньше нуля.
         Обработайте исключение и выведите сообщение.
         */
        try {
            setAge(10);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            setAge(-1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        /**
         * 5. Собственное исключение: депозит
         * Создайте исключение NegativeDepositException, и метод deposit(double amount),
         * который выбрасывает это исключение при отрицательном значении. Обработайте его в main.
         */
        try {
            deposit(100);
        } catch (NegativeDepositException e) {
            System.out.println(e.getMessage());
        }

        try {
            deposit(-1);
        } catch (NegativeDepositException e) {
            System.out.println(e.getMessage());
        }

        /**
         * 6. Поиск товара по коду
         * Реализуйте метод getItem(String code).
         * Если код не найден в карте товаров, выбросите ItemNotFoundException,
         * унаследованное от RuntimeException.
         * Продемонстрируйте поведение в main.
         */
        productItems.put("169409998", "Математика");
        productItems.put("169409334", "Алгебра");
        productItems.put("169409672", "Геометрия");
        productItems.put("169405342", "Окружающий мир");
        productItems.put("169409763", "Русский язык");
        productItems.put("169401342", "English");
        try {
            System.out.printf("Товар по коду %s = %s\n", "01", getItem("169409672"));
        } catch (ItemNotFoundException e) {
            System.out.printf("Ошибка! %s\n", e.getMessage());
        }
        try {
            System.out.printf("Товар по коду %s = %s\n", "02", getItem("169409763"));
        } catch (ItemNotFoundException e) {
            System.out.printf("Ошибка! %s\n", e.getMessage());
        }
        try {
            System.out.printf("Товар по коду %s = %s\n", "04", getItem("169409342"));
        } catch (ItemNotFoundException e) {
            System.out.printf("Ошибка! %s\n", e.getMessage());
        }

        /**
         7. Чтение из файла
         Реализуйте метод readFile(String path), который читает текстовый файл и возвращает список строк.
         Используйте BufferedReader, перехватите IOException, выведите сообщение об ошибке.
         */
        List<String> list = new ArrayList<>();
        String filePath = System.getProperty("user.dir") + "\\src\\exceptions\\NegativeDepositException.java";
        list = readFile(filePath);
        if (!list.isEmpty()) {
            System.out.println("Содержимое файла: " + filePath);
            System.out.println("------------------");
            list.forEach(System.out::println);
            System.out.println("------------------");
        }

        List<String> list1 = new ArrayList<>();
        filePath = "QQQQQ.txt"; // Файл не существует
        list1 = readFile(filePath);
        if (!list1.isEmpty()) {
            System.out.println("Содержимое файла: " + filePath);
            System.out.println("------------------");
            list1.forEach(System.out::println);
            System.out.println("------------------");
        }

        /**
         * 8. Система логина
         * Создайте метод login(String username, String password),
         * в котором логин и пароль проверяются на корректность.
         * Если один из них не совпадает — выбрасывается LoginFailedException.
         * Исключение должно наследоваться от Exception.
         */
        usersItems.put("Petrov", "12345678");
        usersItems.put("Sidorov", "11111111");
        usersItems.put("Ivanov", "222222222");
        usersItems.put("Kulebyakin", "ssddfffg");
        try {
            login("Petrov", "12345678");
        } catch (LoginFailedException e) {
            System.out.printf("Ошибка = %s\n", e.getMessage());
        }
        try {
            login("Kulebyakin", "222222222");
        } catch (LoginFailedException e) {
            System.out.printf("Ошибка! %s\n", e.getMessage());
        }

        /**
         /**
         * 9. Банковский перевод с валидацией
         * Метод transfer(fromAccount, toAccount, amount):
         *  - выбрасывает InvalidTransferAmountException, если сумма <= 0
         *  - выбрасывает InsufficientBalanceException, если баланс отправителя меньше суммы
         *  - содержит try-catch в main
         */
        accountsItems.put("0001001", 1000.0);
        accountsItems.put("0001002", 500.0);
        accountsItems.put("0001003", 105.0);
        accountsItems.put("0001004", 210.0);
        try {
            transfer("0001003", "00010043", 200.0);
            System.out.println("Перевод проведен");
        } catch (Exception e) {
            System.out.printf("Ошибка! %s\n", e.getMessage());
        }
        try {
            transfer("0001003", "0001004", 200.0);
            System.out.println("Перевод проведен");
        } catch (Exception e) {
            System.out.printf("Ошибка! %s\n", e.getMessage());
        }
        try {
            transfer("0001001", "0001004", 200.0);
            System.out.println("Перевод проведен");
        } catch (Exception e) {
            System.out.printf("Ошибка! %s\n", e.getMessage());
        }

        /**
         /**
         * 10. Сервис оценки товара
         * Реализуйте метод rateProduct(String rating), который:
         *  - принимает значение от 1 до 5
         *  - выбрасывает InvalidRatingException (checked), если значение вне диапазона
         *  - сохраняет рейтинг в списке, если всё хорошо
         *  - перехватывает NumberFormatException, если рейтинг пришёл в виде строки, но содержит нечисловое значение
         */
        rateProductsItems.put("HONOR X8b", 5);
        rateProductsItems.put("TECNO Camon 30", 2);
        rateProductsItems.put("Samsung Galaxy A15", 3);
        rateProductsItems.put("TECNO Spark 20 Pro", 1);
        rateProductsItems.put("Xiaomi Poco M6", 4);
        rateProductsItems.put("Infinix NOTE 30i", 4);
        try {
            rateProduct("Realme Note 50", "3");
            System.out.println("Pейтинг установлен");
        } catch (InvalidRatingException e) {
            System.out.printf("Ошибка! %s\n", e.getMessage());
        }
        try {
            rateProduct("TECNO Spark 20 Pro", "33");
            System.out.println("Pейтинг установлен");
        } catch (InvalidRatingException e) {
            System.out.printf("Ошибка! %s\n", e.getMessage());
        }
        try {
            rateProduct("TECNO Spark 20 Pro", "3");
            System.out.println("Pейтинг установлен");
        } catch (InvalidRatingException e) {
            System.out.printf("Ошибка! %s\n", e.getMessage());
        }
    }

    /**
     * 1. безопасное деление
     * напишите метод safedivide(int a, int b), который возвращает a / b.
     * если b == 0, перехватите исключение и выведите сообщение: "деление на ноль запрещено".
     * прикольное задание. заодно узнал, что такое "infinity" в java :))
     */
    public static String safeDivide(int a, int b) {
        double fResult;
        try {
            fResult = a / b;
            return String.valueOf(fResult);
        } catch(ArithmeticException e) {
            if (b==0) {
                return "Деление на ноль запрещено";
            } else {
                return e.getMessage();
            }
        }
    }

    /**
     * 2. Проверка строки
     * Напишите метод, который принимает строку и выбрасывает IllegalArgumentException,
     * если строка пуста или состоит только из пробелов.
     */
    public static void isEmptyString(String str) throws IllegalArgumentException {
        if ((str == null) || str.trim().isEmpty()) {
            throw new IllegalArgumentException("Строка пуста или состоит только из пробелов");
        }
    }

    /**
     * 3. Преобразование строки в число
     * Дан список строк List.of("10", "abc", "5").
     * Преобразуйте его в список чисел, перехватывая NumberFormatException.
     * Ошибки не должны останавливать выполнение.
     */
    public static List<Integer> toIntegerList(List<String> list) {
        List<Integer> intList = new ArrayList<>();
        for (String s : list) {
            try {
                intList.add(Integer.decode(s));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка преобразования: '" + e.getMessage() + "'");
            }
        }
        return intList;
    }

    /**
     4. Простая валидация возраста
     Метод setAge(int age) должен выбрасывать IllegalArgumentException,
     если возраст меньше нуля.
     Обработайте исключение и выведите сообщение.
     */
    public static void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть меньше нуля");
        }
    }

    /**
     * 5. Собственное исключение: депозит
     * Создайте исключение NegativeDepositException, и метод deposit(double amount),
     * который выбрасывает это исключение при отрицательном значении. Обработайте его в main.
     */
    public static void deposit(double amount) throws NegativeDepositException {
        if (amount < 0) {
            throw new NegativeDepositException(amount);
        }
    }

    /**
     * 6. Поиск товара по коду
     * Реализуйте метод getItem(String code).
     * Если код не найден в карте товаров, выбросите ItemNotFoundException,
     * унаследованное от RuntimeException.
     * Продемонстрируйте поведение в main.
     */
    public static String getItem(String code) throws ItemNotFoundException {
        if (productItems.containsKey(code)) {
            return productItems.get(code);
        } else {
            throw new ItemNotFoundException(code);
        }
    }

    /**
     7. Чтение из файла
     Реализуйте метод readFile(String path), который читает текстовый файл и возвращает список строк.
     Используйте BufferedReader, перехватите IOException, выведите сообщение об ошибке.
     */
    public static List<String> readFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.printf("Ошибка чтения файла: %s\n", e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * 8. Система логина
     * Создайте метод login(String username, String password),
     * в котором логин и пароль проверяются на корректность.
     * Если один из них не совпадает — выбрасывается LoginFailedException.
     * Исключение должно наследоваться от Exception.
     */
    public static void login(String username, String password) throws LoginFailedException {
        if (!usersItems.containsKey(username) || !usersItems.get(username).equals(password)) {
            throw new LoginFailedException();
        }
    }

    /**
     * 9. Банковский перевод с валидацией
     * Метод transfer(fromAccount, toAccount, amount):
     *  - выбрасывает InvalidTransferAmountException, если сумма <= 0
     *  - выбрасывает InsufficientBalanceException, если баланс отправителя меньше суммы
     *  - содержит try-catch в main
     */
    public static void transfer(String fromAccount, String toAccount, Double amount) throws InvalidTransferAmountException, InsufficientBalanceException {
        if (fromAccount == null || toAccount == null || amount == null ||
           !accountsItems.containsKey(fromAccount) || !accountsItems.containsKey(toAccount)) {
            throw new IllegalArgumentException("Не верные значения fromAccount, toAccount, amount");
        }
        if (amount <= 0) {
            throw new InvalidTransferAmountException(amount);
        }
        if (accountsItems.get(fromAccount) < amount) {
            throw new InsufficientBalanceException(amount);
        }
        accountsItems.put(fromAccount, accountsItems.get(fromAccount) - amount);
        accountsItems.put(toAccount, accountsItems.get(toAccount) + amount);
    }

    /**
     * 10. Сервис оценки товара
     * Реализуйте метод rateProduct(String rating), который:
     *  - принимает значение от 1 до 5
     *  - выбрасывает InvalidRatingException (checked), если значение вне диапазона
     *  - сохраняет рейтинг в списке, если всё хорошо
     *  - перехватывает NumberFormatException, если рейтинг пришёл в виде строки, но содержит нечисловое значение
     */
    public static void rateProduct(String strProduct, String strRating) throws InvalidRatingException {
        int rate;
        if (strProduct == null || strRating == null) {
            throw new IllegalArgumentException("Не заданы наименование товара или значение рейтинга");
        }
        try {
            rate = Integer.parseInt(strRating);
            if (rate < 1 || rate > 5) {
                throw new InvalidRatingException(rate);
            }
            rateProductsItems.put(strProduct, rate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Не верное значения рейтинга '" + strRating + "'");
        }
    }

}

