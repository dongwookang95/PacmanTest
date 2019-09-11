package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test suite to confirm that {@link Unit}s correctly (de)occupy squares.
 *
 * @author Jeroen Roosen 
 *
 */
class OccupantTest {

    /**
     * The unit under test.
     */
    private Unit unit;

    /**
     * Resets the unit under test.
     */
    @BeforeEach
    void setUp() {
        unit = new BasicUnit();
    }

    /**
     * Asserts that a unit has no square to start with.
     */
    @Test
    void noStartSquare() {
        boolean result = unit.hasSquare();
        assertThat(result).isEqualTo(false);
    }

    /**
     * Tests that the unit indeed has the target square as its base after
     * occupation.
     */
    @Test
    void testOccupy() {
        Square test = new BasicSquare();
        unit.occupy(test);
        boolean result = unit.hasSquare();
        Square result2 = unit.getSquare();
        assertThat(result).isEqualTo(true);
        assertThat(result2).isEqualTo(test);

    }

    /**
     * Test that the unit indeed has the target square as its base after
     * double occupation.
     */
    @Test
    void testReoccupy() {
        Square test = new BasicSquare();
        unit.occupy(test); //targeting different square.
        unit.occupy(test); //targeting initial square in line 61.

        boolean result = unit.hasSquare();
        Square result2 = unit.getSquare();

        assertThat(result).isEqualTo(true);
        assertThat(result2).isEqualTo(test);
    }
}
