package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;


/**
 * test a single player game.
 */
class GameUnitTest {

    @Mock
    private Game testGame = mock(Game.class);

    @Mock
    private Player testPlayer = mock(Player.class);

    @Mock
    private Level testLevel = mock(Level.class);

    @Mock
    private PointCalculator pointCalculator = mock(PointCalculator.class);


    /**
     * initialise the game.
     */
    @BeforeEach
    void initialise() {
        testGame = new SinglePlayerGame(testPlayer, testLevel, pointCalculator);
    }


    /**
     * Check all true statements.
     */
    @Test
    void startGame() {
        when(testLevel.remainingPellets()).thenReturn(2);
        when(testLevel.isAnyPlayerAlive()).thenReturn(true);

        testGame.start();
        testGame.start();

        verify(testLevel, times(1)).start();
    }


    /**
     * When only one statement(remainingPellets() > 0) is true.
     */
    @Test
    void startGame2() {
        when(testLevel.remainingPellets()).thenReturn(2);
        when(testLevel.isAnyPlayerAlive()).thenReturn(false);

        testGame.start();

        verify(testLevel, times(0)).addObserver(testGame);
        verify(testLevel, times(0)).start();
        assertFalse(testGame.isInProgress());
    }

    /**
     * When only one statement(isAnyPlayerAlive()) is true.
     */
    @Test
    void startGame3() {
        when(testLevel.remainingPellets()).thenReturn(0);
        when(testLevel.isAnyPlayerAlive()).thenReturn(true);

        testGame.start();

        verify(testLevel, times(0)).addObserver(testGame);
        verify(testLevel, times(0)).start();
        assertFalse(testGame.isInProgress());
    }

    /**
     * Check all false statements.
     */
    @Test
    void start_all_False() {
        when(testLevel.remainingPellets()).thenReturn(0);
        when(testLevel.isAnyPlayerAlive()).thenReturn(false);

        testGame.start();

        verify(testLevel, times(0)).addObserver(testGame);
        verify(testLevel, times(0)).start();
        assertFalse(testGame.isInProgress());
    }
}
