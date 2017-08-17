import org.junit.*;
import static org.junit.Assert.*;

public class SuperTest {
    @Test
    public void SuperHero_instantiatesCorrectly_true() {
        Super superHero = new Super("Superman", 31, "Invincibility", "Kryptonite");
        assertEquals(true, superHero instanceof Super);
    }

    @Test
    public void Super_instantiatesWithDescription_String() {
      Super superHero = new Super("Superman", 31, "Invincibility", "Kryptonite");
      assertEquals("Superman", superHero.getName());
    }

    @Test
    public void all_returnsAllInstancesOfSuper_true() {
      Super firstSuper = new Super("Superman", 33, "Invincibility", "Kryponite");
      Super secondSuper = new Super("Batman", 45, "Super Rich", "Flying");
      assertTrue(Super.all().contains(firstSuper));
      assertTrue(Super.all().contains(secondSuper));
    }
}