package nl.tudelft.jpacman.integration.action;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 *
    Scenario S2.4: The player dies
        Given the game has started,
        and  my Pacman is next to a cell containing a ghost;
        When  I press an arrow key towards that square;
        Then  my Pacman dies,
        and  the game is over.
 */
public class PlayerDies {
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
     * Test when player dies.
     */
    @Test
    public void playerDies() {
        testLauncher.withMapFile("/newMap.txt");
        startGame();
        testPlayers = testLauncher.getGame().getPlayers();
        Player workingPlayer = testPlayers.get(0);

        testLauncher.getGame().move(workingPlayer, Direction.EAST);

        assertFalse(workingPlayer.isAlive());
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
