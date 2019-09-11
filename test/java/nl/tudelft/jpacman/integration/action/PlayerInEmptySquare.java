package nl.tudelft.jpacman.integration.action;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * The player moves on empty square
 * Given the game has started,
 *  and  my Pacman is next to an empty square;
 * When  I press an arrow key towards that square;
 * Then  my Pacman can move to that square
 *  and  my points remain the same.
 */

public class PlayerInEmptySquare {
    private Launcher testLauncher;
    private List<Player> testPlayers;
    private Player workingPlayer;

    /**
     * initialise launcher.
     */
    @BeforeEach
    public void initialise() {
        testLauncher = new Launcher();

    }

    /**
     * Test when a player is playing game in empty square.
     */
    @Test
    public void playerInEmptySquare() {
        testLauncher.launch();
        testLauncher.getGame().start();
        testPlayers = testLauncher.getGame().getPlayers();
        workingPlayer = testPlayers.get(0);
        testLauncher.getGame().move(workingPlayer, Direction.WEST);
        int score = workingPlayer.getScore();

        Square playSquare = workingPlayer.getSquare();
        assertThat(playSquare.getSquareAt(Direction.EAST).getOccupants()).isEmpty();

        testLauncher.getGame().move(workingPlayer, Direction.EAST);
        Square playSquare2 = workingPlayer.getSquare();

        assertThat(playSquare.getSquareAt(Direction.EAST)).isEqualTo(playSquare2);
        // Check if the player moved in the right direction.


        assertThat(workingPlayer.getScore()).isEqualTo(score);

    }

}




