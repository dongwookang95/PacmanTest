package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;



/**
 * Couple of tests that check if the different game
 * states are working properly in a multi level game.
 */
public class MultiLevelLauncherTest {

    private MultiLevelLauncher multiLevelLauncher;
    private Level.LevelObserver levelObserver;

    /**
     * Setting the multiLevelLauncher before every test.
     */
    protected void setUp() {
        multiLevelLauncher = new MultiLevelLauncher();
        multiLevelLauncher.withMapFile("/board2.txt");
        levelObserver = Mockito.mock(Level.LevelObserver.class);
        multiLevelLauncher.launch();
    }





    /**
     * check whether it goes to next level when the player wins.
     */
    @Test
    void secondLevelStartTest() {
        setUp();
        Level firstLevel = multiLevelLauncher.getGame().getLevel();
        Player player = multiLevelLauncher.getGame().getPlayers().get(0);
        firstLevel.addObserver(levelObserver);

        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);
        multiLevelLauncher.getGame().start();

        assertTrue(multiLevelLauncher.getGame().isInProgress());
        assertNotEquals(firstLevel, multiLevelLauncher.getGame().getLevel());
        Mockito.verify(levelObserver).levelWon();
    }


    /**
     * Test case when the player wins the second level.
     */
    @Test
    void secondLevelWinTest() {
        setUp();
        Level beginnerLevel = multiLevelLauncher.getGame().getLevel();
        Player player = multiLevelLauncher.getGame().getPlayers().get(0);
        beginnerLevel.addObserver(levelObserver);

        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);

        beginnerLevel = multiLevelLauncher.getGame().getLevel();
        beginnerLevel.addObserver(levelObserver);
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);

        assertFalse(multiLevelLauncher.getGame().isInProgress());
        Mockito.verify(levelObserver, Mockito.times(2)).levelWon();
    }

    /**
     * check whether it goes to next level when the player wins.
     */
    @Test
    void thirdLevelStartTest() {
        setUp();
        Level firstLevel = multiLevelLauncher.getGame().getLevel();
        Player player = multiLevelLauncher.getGame().getPlayers().get(0);
        firstLevel.addObserver(levelObserver);

        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);
        multiLevelLauncher.getGame().start();

        assertTrue(multiLevelLauncher.getGame().isInProgress());
        Mockito.verify(levelObserver).levelWon();
    }


    /**
     * Test case when the player wins the third level.
     */
    @Test
    void thirdLevelWinTest() {
        setUp();
        Level firstLevel = multiLevelLauncher.getGame().getLevel();
        Player player = multiLevelLauncher.getGame().getPlayers().get(0);
        firstLevel.addObserver(levelObserver);

        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);

        firstLevel = multiLevelLauncher.getGame().getLevel();
        firstLevel.addObserver(levelObserver);
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);

        firstLevel = multiLevelLauncher.getGame().getLevel();
        firstLevel.addObserver(levelObserver);
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);

        assertFalse(multiLevelLauncher.getGame().isInProgress());
    }

    /**
     * Test case when the player wins the third level.
     */
    @Test
    void fourthLevelStartTest() {
        setUp();
        Level firstLevel = multiLevelLauncher.getGame().getLevel();
        Player player = multiLevelLauncher.getGame().getPlayers().get(0);
        firstLevel.addObserver(levelObserver);

        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().gameWon();

        assertTrue(multiLevelLauncher.getGame().isInProgress());
        Mockito.verify(levelObserver).levelWon();
    }


    /**
     * Test case when the player wins the fourth level.
     */
    @Test
    void fourthLevelWinTest() {
        setUp();
        Player player = multiLevelLauncher.getGame().getPlayers().get(0);
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);
        assertFalse(multiLevelLauncher.getGame().isInProgress());
    }

    /**
     * Test when the game level gets reached to maximum level.
     */
    @Test
    void maxLevelTest() {
        setUp();
        Level firstLevel = multiLevelLauncher.getGame().getLevel();
        Player player = multiLevelLauncher.getGame().getPlayers().get(0);
        multiLevelLauncher.getGame().start();
        firstLevel.addObserver(levelObserver);
        multiLevelLauncher.getGame().move(player, Direction.WEST);

        firstLevel = multiLevelLauncher.getGame().getLevel();
        firstLevel.addObserver(levelObserver);
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);

        firstLevel = multiLevelLauncher.getGame().getLevel();
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);

        firstLevel = multiLevelLauncher.getGame().getLevel();
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);

        firstLevel = multiLevelLauncher.getGame().getLevel();
        multiLevelLauncher.getGame().start();

        assertFalse(multiLevelLauncher.getGame().isInProgress());
    }

    /**
     * when the player lose at the first round.
     */
    @Test
    void firstLevelloseTest() {
        setUp();
        Level firstLevel = multiLevelLauncher.getGame().getLevel();
        Player player = multiLevelLauncher.getGame().getPlayers().get(0);
        firstLevel.addObserver(levelObserver);

        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.EAST);
        multiLevelLauncher.getGame().move(player, Direction.EAST);
        multiLevelLauncher.getGame().move(player, Direction.SOUTH);
        multiLevelLauncher.getGame().move(player, Direction.EAST);

        assertFalse(multiLevelLauncher.getGame().isInProgress());
        Mockito.verify(levelObserver).levelLost();
    }

    /**
     * when the player lose at the second round.
     */
    @Test
    void secondLevelloseTest() {
        setUp();
        Level firstLevel = multiLevelLauncher.getGame().getLevel();
        Player player = multiLevelLauncher.getGame().getPlayers().get(0);
        firstLevel.addObserver(levelObserver);

        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.WEST);

        firstLevel = multiLevelLauncher.getGame().getLevel();
        firstLevel.addObserver(levelObserver);
        multiLevelLauncher.getGame().start();
        multiLevelLauncher.getGame().move(player, Direction.EAST);
        multiLevelLauncher.getGame().move(player, Direction.EAST);
        multiLevelLauncher.getGame().move(player, Direction.SOUTH);
        multiLevelLauncher.getGame().move(player, Direction.EAST);

        assertFalse(multiLevelLauncher.getGame().isInProgress());
        Mockito.verify(levelObserver).levelLost();
    }


}
