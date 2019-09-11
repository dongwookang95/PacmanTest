package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Simple test that tests Inky's movement within a level.
 */

class InkyTest {

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
     * Test when the player and Blinky are close to Inky (Good weather).
     */
    @Test
    void nearTest() {
        ArrayList<String> testLevel = new ArrayList<>();
        testLevel.add("#############");
        testLevel.add("#PBI        #");
        testLevel.add("#############");
        level = parser.parseMap(testLevel);
        level.registerPlayer(player);
        player.setDirection(Direction.WEST);
        Inky i = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        if (i != null) {
            assertThat(i.nextAiMove()).isEqualTo(Optional.of(Direction.EAST));
        }
    }

    /**
     * Test when the player and Blinky are far from Inky (Good weather).
     */
    @Test
    void farTest() {
        ArrayList<String> testLevel = new ArrayList<>();
        testLevel.add("#############");
        testLevel.add("#PB        I#");
        testLevel.add("#############");
        level = parser.parseMap(testLevel);
        level.registerPlayer(player);
        player.setDirection(Direction.NORTH);
        Inky i = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        if (i != null) {
            assertThat(i.nextAiMove()).isEqualTo(Optional.empty());
        }
    }

    /**
     * Test when the player is far from Blinky and Inky (Good weather).
     */
    @Test
    void far2Test() {
        ArrayList<String> testLevel = new ArrayList<>();
        testLevel.add("#############");
        testLevel.add("#P        BI#");
        testLevel.add("#############");
        level = parser.parseMap(testLevel);
        player.setDirection(Direction.EAST);
        Inky i = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        if (i != null) {
            assertThat(i.nextAiMove()).isEqualTo(Optional.empty());
        }
    }

    /**
     * Test when the player is non-existent (Bad weather).
     */
    @Test
    void noPlayerTest() {
        ArrayList<String> testLevel = new ArrayList<>();
        testLevel.add("#############");
        testLevel.add("#         BI#");
        testLevel.add("#############");
        level = parser.parseMap(testLevel);
        Inky i = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        if (i != null) {
            assertThat(i.nextAiMove()).isEqualTo(Optional.empty());
        }
    }

    /**
     * Test when Blinky is non-existent (Bad weather).
     */
    @Test
    void noBlinkyTest() {
        ArrayList<String> testLevel = new ArrayList<>();
        testLevel.add("#############");
        testLevel.add("#         PI#");
        testLevel.add("#############");
        level = parser.parseMap(testLevel);
        player.setDirection(Direction.EAST);
        Inky i = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        if (i != null) {
            assertThat(i.nextAiMove()).isEqualTo(Optional.empty());
        }
    }

    /**
     * Test when the player and Blinky are non-existent (Bad weather).
     */
    @Test
    void noBlinkyAndPlayerTest() {
        ArrayList<String> testLevel = new ArrayList<>();
        testLevel.add("#############");
        testLevel.add("#          I#");
        testLevel.add("#############");
        level = parser.parseMap(testLevel);
        Inky i = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        if (i != null) {
            assertThat(i.nextAiMove()).isEqualTo(Optional.empty());
        }
    }

    /**
     * Test when Inky has no path (Bad weather).
     */
    @Test
    void noPathTest() {
        ArrayList<String> testLevel = new ArrayList<>();
        testLevel.add("#############");
        testLevel.add("#PB#I       #");
        testLevel.add("#############");
        level = parser.parseMap(testLevel);
        level.registerPlayer(player);
        player.setDirection(Direction.WEST);
        Inky i = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        if (i != null) {
            assertThat(i.nextAiMove()).isEqualTo(Optional.of(Direction.EAST));
        }
    }

}
