import org.junit.*;
import static org.junit.Assert.*;

public class SquadTest {
    @Test
    public void Squad_ifItInstatiates_true() {
        Squad squadTest = new Squad("Avengers", 5, "Save The Earth");
        assertTrue(squadTest instanceof Squad);
    }

    @Test
    public void all_returnAllHeroObjectsCreated_true() {
        Squad squadTest = new Squad("Avengers", 5, "Save The Earth");
        Squad squadTest2 = new Squad("Justice League", 7, "Save The Universe");
        assertEquals(true, Squad.all().contains(squadTest));
        assertEquals(true, Squad.all().contains(squadTest2));
    }

    @Test
    public void clear_emptiesAllSquadsCreated_0() {
        Squad.clear();
        assertEquals(Squad.all().size(), 0);
    }

    //finds a squad with a certain id
    @Test
    public void find_returnsSquadWithSameId_testSquadTwo() {
        Squad squadTest = new Squad("Avengers", 5, "Save The Earth");
        assertEquals(Squad.find(squadTest.getId()), squadTest);
    }
}