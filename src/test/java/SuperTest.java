import org.junit.*;
import static org.junit.Assert.*;

public class SuperTest {
    @Test
    public void SuperHero_instantiatesCorrectly_true() {
        Super superHero = new Super("Superman");
        assertEquals(true, superHero instanceof Super);
    }
}