import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
   private final String horseName = "Spirit";
    private final String messageNull = "Name cannot be null.";
    private final String messageBlank = "Name cannot be blank.";
    private final String messageNegativeSpeed = "Speed cannot be negative.";
    private final String messageNegativeDistance = "Distance cannot be negative.";
    private final Double  expectedSpeed = 5.0;
    private final Double expectedDistance = 10.0;
    private final String[] blankNames = {"",  " ", "\t", "\n","\r", "\f","\u000B"};

    @Test
    public void testConstructorWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, expectedSpeed, expectedDistance));
    }

    @Test
    public void testConstructorWithNullNameMessage() {

        try {
            new Horse(null, expectedSpeed, expectedDistance);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(messageNull, e.getMessage());
        }
    }

    @Test
    public void testConstructorWithBlankName() {
        for (String name : blankNames) {
            assertThrows(IllegalArgumentException.class, () -> new Horse(name, 5.0, 10.0));
        }
    }

    @Test
    public void testConstructorWithBlankNameMessage() {
        for (String name : blankNames) {
            try {
                new Horse(name, expectedSpeed, expectedDistance);
            } catch (IllegalArgumentException e) {
                assertEquals(messageBlank, e.getMessage());
            }
        }
    }

    @Test
    public void testConstructorWithNegativeSpeed() {

        assertThrows(IllegalArgumentException.class, () -> new Horse(horseName, -expectedSpeed, expectedDistance));
    }

    @Test
    public void testConstructorWithNegativeSpeedMessage() {
        try {
            new Horse(horseName, -expectedSpeed, expectedDistance);
        } catch (IllegalArgumentException e) {
            assertEquals(messageNegativeSpeed, e.getMessage());
        }
    }

    @Test
    public void testConstructorWithNegativeDistance() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(horseName, expectedSpeed, -expectedDistance));
    }

    @Test
    public void testConstructorWithNegativeDistanceMessage() {
        try {
            new Horse(horseName, expectedSpeed, -expectedDistance);
        } catch (IllegalArgumentException e) {
            assertEquals(messageNegativeDistance, e.getMessage());
        }
    }

    @Test
    public void testGetNameConstructor() {
        Horse horse = new Horse(horseName, expectedSpeed, expectedDistance);
        assertEquals(horseName, horse.getName());
    }
    @Test
    public void testGetNameField() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse(horseName, expectedSpeed, expectedDistance);
        Field fieldName = Horse.class.getDeclaredField("name");
        fieldName.setAccessible(true);
        String name = (String) fieldName.get(horse);
        assertEquals(horseName, name);
    }

    @Test
    public void testGetSpeedConstructor() {
        Horse horse = new Horse(horseName, expectedSpeed, expectedDistance);
        assertEquals(expectedSpeed, horse.getSpeed());
    }
    @Test
    public void testGetSpeedField() {
        Horse horse = new Horse(horseName, expectedSpeed, expectedDistance);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    public void testGetDistanceConstructor() {
        Horse horse = new Horse(horseName, expectedSpeed, expectedDistance);
        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    public void testGetDistanceWithTwoParameterConstructor() {
        Horse horse = new Horse(horseName, expectedSpeed);
        assertEquals(0.0, horse.getDistance());
    }


    @Test
    public void testMove() {
        try (MockedStatic<Horse> horseMock = mockStatic(Horse.class)) {
        new Horse(horseName, expectedSpeed, expectedDistance).move();
horseMock.verify(()-> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}