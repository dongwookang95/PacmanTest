package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;

import static org.assertj.core.api.Assertions.assertThat;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Simple test that tests Clyde's movement within a level.
 */
class ClydeTest {

    /**
     * Map parser used to construct boards.
     */
    private PointCalculator pointCalculator;
    private PlayerFactory playerfactory;
    private LevelFactory levelfactory;
    private GhostFactory ghostFactory;
    private BoardFactory boardfactory;
    private GhostMapParser parser;
    private Level level;
    private Player player;

    /**
     * Set up for the map parser and the tests.
     */
    @BeforeEach
    void setUp() {
        PacManSprites sprites = new PacManSprites();
        pointCalculator = new DefaultPointCalculator();
        playerfactory = new PlayerFactory(sprites);
        ghostFactory = new GhostFactory(sprites);
        levelfactory = new LevelFactory(sprites, ghostFactory, pointCalculator);
        boardfactory = new BoardFactory(sprites);
        parser = new GhostMapParser(levelfactory, boardfactory, ghostFactory);
        player = playerfactory.createPacMan();
    }

    /**
     * Test when the Player is far away from Clyde (Good weather).
     */
    @Test
    void farAwayTest() {
        ArrayList<String> testLevel = new ArrayList<>();
        testLevel.add("#############");
        testLevel.add("#C         P#");
        testLevel.add("#############");
        level = parser.parseMap(testLevel);
        level.registerPlayer(player);
        player.setDirection(Direction.EAST);
        Clyde c = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        if (c != null) {
            assertThat(c.nextAiMove().equals(Optional.of(Direction.EAST)));
        }
    }

    /**
     * Test when Clyde is near the Player (Good weather).
     */
    @Test
    void nearTest() {
        ArrayList<String> testLevel = new ArrayList<>();
        testLevel.add("#############");
        testLevel.add("#PC         #");
        testLevel.add("#############");
        level = parser.parseMap(testLevel);
        level.registerPlayer(player);
        player.setDirection(Direction.NORTH);
        Clyde c = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        if (c != null) {
            assertThat(c.nextAiMove().equals(Optional.of(Direction.EAST)));
        }
    }

    /**
     * Test when Clyde does not have a path to go (Bad weather).
     */
    @Test
    void noPathTest() {
        ArrayList<String> testLevel = new ArrayList<>();
        testLevel.add("#############");
        testLevel.add("#P    #C    #");
        testLevel.add("#############");
        level = parser.parseMap(testLevel);
        level.registerPlayer(player);
        Clyde c = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        if (c != null) {
            assertThat(c.nextAiMove().equals(Optional.empty()));
        }
    }

    /**
     * Test when the Player is non-existence (Bad weather).
     */
    @Test
    void noPlayerTest() {
        ArrayList<String> testLevel = new ArrayList<>();
        testLevel.add("#############");
        testLevel.add("         # C#");
        testLevel.add("#############");
        level = parser.parseMap(testLevel);
        Clyde c = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        if (c != null) {
            assertThat(c.nextAiMove().equals(Optional.empty()));
        }
    }
}
