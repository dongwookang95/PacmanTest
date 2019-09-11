package nl.tudelft.jpacman.integration.action;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 *
 Scenario S2.5: Player wins, extends S2.1
 When  I have eaten the last pellet;
 Then  I win the game.
 */
public class PlayerAlive {
    private Launcher testLauncher;
    private List<Player> testPlayers;

    /**
     * initialise the game.
     */
    @BeforeEach
    public void initialise() {
        testLauncher = new Launcher();

    }

    /**
     * Test a situation that player win.
     */
    @Test
    public void playerAliveTest() {
        String a = "/newMap.txt";
        startGame();
        testLauncher.withMapFile(a);
        testLauncher.launch();
        testLauncher.getGame().start();
        testPlayers = testLauncher.getGame().getPlayers();
        Player workingPlayer = testPlayers.get(0);
        testLauncher.getGame().move(workingPlayer, Direction.SOUTH);
        assertTrue(workingPlayer.isAlive());
        assertEquals(0, testLauncher.getGame().getLevel().remainingPellets());
        assertFalse(testLauncher.getGame().isInProgress());
    }


    /**
     * start game method.
     */
    public void startGame() {
        testLauncher.launch();
        testLauncher.getGame().start();
    }
}

