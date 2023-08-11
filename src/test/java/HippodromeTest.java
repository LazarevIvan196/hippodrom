import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void testConstructorWithNull() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }


    @Test
    public void testConstructorWithEmptyHorsesList() {
        List<Horse> emptyList = new ArrayList<>();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyList));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void testGetHorsesReturnsSameList() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, 5.0, 0.0));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> returnedHorses = hippodrome.getHorses();

        assertIterableEquals(horses, returnedHorses);
    }

    @Test
    public void testMoveCallsMoveForAllHorses() {
        List<Horse> horseMocks = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horseMock = Mockito.mock(Horse.class);
            horseMocks.add(horseMock);
        }
        Hippodrome hippodrome = new Hippodrome(horseMocks);
        hippodrome.move();
        for (Horse horseMock : horseMocks) {
            Mockito.verify(horseMock).move();
        }
    }

    @Test
    public void testGetWinnerReturnsHorseWithMaxDistance() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Spirit", 5.0, 10.0));
        horses.add(new Horse("Wild", 6.0, 15.0));
        horses.add(new Horse("Bob", 4.0, 12.0));
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();
        assertSame(horses.get(1), winner);
    }
}
