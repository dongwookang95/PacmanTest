package nl.tudelft.jpacman.integration.action;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 *

The player consumes
    Given the game has started,
    and  my Pacman is next to a square containing a pellet;
    When  I press an arrow key towards that square;
    Then  my Pacman can move to that square,
    and  I earn the points for the pellet,
    and  the pellet disappears from that square.
 */
public class PlayerConsume {
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
     *     The player consumes
     *     Given the game has started,
     *     and  my Pacman is next to a square containing a pellet.
     *
     **/
    @Test
    public void playerConsumeTest() {
        startGame();
        testPlayers = testLauncher.getGame().getPlayers();
        workingPlayer = testPlayers.get(0);

        Square squareForPlayer = workingPlayer.getSquare();
        Square squareForPellet = squareForPlayer.getSquareAt(Direction.WEST);

        List<Unit> units = squareForPellet.getOccupants();
        Pellet workingPellet = (Pellet) units.get(0);
        testLauncher.getGame().move(workingPlayer, Direction.WEST);
        Square checkSquare = workingPlayer.getSquare();
        assertThat(workingPlayer.getScore()).isEqualTo(workingPellet.getValue());
        assertThat(squareForPlayer.getSquareAt(Direction.WEST)).isEqualTo(checkSquare);
        assertThat(checkSquare.getOccupants().size()).isEqualTo(1);
        assertThat(checkSquare.getOccupants().get(0)).isEqualTo(workingPlayer);
    }

    /**
     * start game method.
     */
    public void startGame() {
        testLauncher.launch();
        testLauncher.getGame().start();

    }
}
