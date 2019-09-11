package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Couple of tests that check if the different game states are working properly.
 */
public class GameTest {

    private Launcher launcher;
    private Level.LevelObserver levelObserver = Mockito.mock(Level.LevelObserver.class);

    /**
     * Setting the launcher before every test.
     */
    @BeforeEach
    void setUp() {
        launcher = new Launcher();
        set(launcher);
    }

    /**
     * Setting the launcher to the launcher that will be parsed.
     * @param launcher The launcher that is going to be used for the test.
     */
    void set(Launcher launcher) {
        this.launcher = launcher;
    }

    /**
     * Closing the launcher after every test.
     */
    @AfterEach
    void after() {
        launcher.dispose();
    }

    /**
     * Test if the game is won if the last pellet is eaten.
     */
    @Test
    void winTest() {
        launcher.withMapFile("/winTest.txt");
        launcher.launch();
        launcher.getGame().start();
        launcher.getGame().getLevel().addObserver(levelObserver);
        Player player = launcher.getGame().getPlayers().get(0);
        launcher.getGame().move(player, Direction.EAST);

        Mockito.verify(levelObserver).levelWon();
        assertTrue(player.isAlive());
        assertEquals(0, launcher.getGame().getLevel().remainingPellets());
        assertFalse(launcher.getGame().isInProgress());
    }

    /**
     * Test if the game is closed and lost when Pacman dies.
     */
    @Test
    void lostTest() {
        launcher.withMapFile("/lostTest.txt");
        launcher.launch();
        launcher.getGame().start();
        launcher.getGame().getLevel().addObserver(levelObserver);
        Player player = launcher.getGame().getPlayers().get(0);
        launcher.getGame().move(player, Direction.EAST);
        launcher.getGame().move(player, Direction.EAST);

        Mockito.verify(levelObserver).levelLost();
        assertFalse(launcher.getGame().isInProgress());
    }

    /**
     * Test if the pause button works.
     */
    @Test
    void pauseTest() {
        launcher.withMapFile("/pauseTest.txt");
        launcher.launch();
        Player player = launcher.getGame().getPlayers().get(0);
        launcher.getGame().start();
        launcher.getGame().stop();

        assertTrue(player.isAlive());
        assertFalse(launcher.getGame().isInProgress());
    }

    /**
     * Test if the game continues after a pause.
     */
    @Test
    void pauseStartTest() {
        launcher.withMapFile("/pauseTest.txt");
        launcher.launch();
        Player player = launcher.getGame().getPlayers().get(0);
        launcher.getGame().start();
        player.setDirection(Direction.EAST);
        launcher.getGame().stop();
        launcher.getGame().start();
        player.setDirection(Direction.EAST);

        assertTrue(player.isAlive());
        assertTrue(launcher.getGame().isInProgress());
    }

    /**
     * Test if the game can be won after a pause is introduced.
     */
    @Test
    void pauseWinTest() {
        launcher.withMapFile("/pauseTest.txt");
        launcher.launch();
        Player player = launcher.getGame().getPlayers().get(0);
        launcher.getGame().getLevel().addObserver(levelObserver);
        launcher.getGame().start();
        launcher.getGame().move(player, Direction.EAST);

        launcher.getGame().stop(); //introducing the pause

        launcher.getGame().start();
        launcher.getGame().move(player, Direction.EAST);
        launcher.getGame().move(player, Direction.EAST);

        Mockito.verify(levelObserver).levelWon();
        assertTrue(player.isAlive());
        assertEquals(0, launcher.getGame().getLevel().remainingPellets());
        assertFalse(launcher.getGame().isInProgress());
    }

    /**
     * Test if the game can be lost after a pause is introduced.
     */
    @Test
    void pauseLoseTest() {
        launcher.withMapFile("/lostTest.txt");
        launcher.launch();
        Player player = launcher.getGame().getPlayers().get(0);
        launcher.getGame().start();
        launcher.getGame().getLevel().addObserver(levelObserver);
        launcher.getGame().move(player, Direction.EAST);

        launcher.getGame().stop(); //introducing the pause

        launcher.getGame().start();
        launcher.getGame().move(player, Direction.EAST);

        Mockito.verify(levelObserver).levelLost();
        assertFalse(launcher.getGame().isInProgress());
    }

}
