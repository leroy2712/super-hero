import org.junit.*;
import static org.junit.Assert.*;

public class HeroTest {
    @Test
    public void SuperHero_instantiatesCorrectly_true() {
        Hero superHero = new Hero("Superman", 31, "Invincibility", "Kryptonite");
        assertEquals(true, superHero instanceof Hero);
    }

    @Test
    public void Super_instantiatesWithDescription_String() {
      Hero superHero = new Hero("Superman", 31, "Invincibility", "Kryptonite");
      assertEquals("Superman", superHero.getName());
    }

    @Test
    public void all_returnsAllInstancesOfSuper_true() {
      Hero firstHero = new Hero("Superman", 33, "Invincibility", "Kryponite");
      Hero secondHero = new Hero("Batman", 45, "Super Rich", "Flying");
      assertTrue(Hero.all().contains(firstHero));
      assertTrue(Hero.all().contains(secondHero));
    }
}