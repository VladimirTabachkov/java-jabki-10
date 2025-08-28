import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class MainTest {
    @Test
    void setAgeTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.setAge(-10);
        });
    }
}