package nl.tudelft.jpacman.integration.action;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;



/**
 *
 Scenario S2.3: The move fails
 Given the game has started,
 and my Pacman is next to a cell containing a wall;
 When  I press an arrow key towards that cell;
 Then  the move is not conducted.
 */
public class PlayerMoveFail {
    private Launcher testLauncher;
    private List<Player> testPlayers;

    /**
     * initialise the launcher.
     */
    @BeforeEach
    public void initialise() {
        testLauncher = new Launcher();
    }

    /**
     * 1. check Pacman is net to a wall.
     * 2. check whether the game is in progress after move to wall.
     * 3. check whether the player is moved or not.
     */
    @Test
    public void playMoveFailTest() {
        testLauncher.withMapFile("/custommap.txt");
        testLauncher.launch();
        testLauncher.getGame().start();
        testPlayers = testLauncher.getGame().getPlayers();
        Player workingPlayer = testPlayers.get(0);

        Square workingSquare = workingPlayer.getSquare();
        boolean check = workingSquare.getSquareAt(Direction.WEST).isAccessibleTo(workingPlayer);
        assertFalse(check);
        Square newWorkingSquare = workingPlayer.getSquare();
        testLauncher.getGame().move(workingPlayer, Direction.WEST);
        boolean check2 = testLauncher.getGame().isInProgress();
        assertTrue(check2);
        assertThat(newWorkingSquare).isEqualTo(workingSquare);


    }

}

