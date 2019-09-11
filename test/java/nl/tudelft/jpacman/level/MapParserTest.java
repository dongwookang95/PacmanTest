package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;





/**
 * MapParserTest.
 */
class MapParserTest {


    @Mock
    private Square[][] test = new Square[1][1];
    private List<Ghost> testGhost = new ArrayList<>();
    private List<Square> testSquare = new ArrayList<>();
    private List<String> grid;
    private Square ground = mock(Square.class);
    private Square wall = mock(Square.class);
    private Pellet pellet = mock(Pellet.class);
    private Ghost ghost = mock(Ghost.class);


    @Mock
    private LevelFactory levelFactory = mock(LevelFactory.class);

    @Mock
    private BoardFactory boardFactory = mock(BoardFactory.class);

    @InjectMocks
    private MapParser mapParser = Mockito.spy(new MapParser(levelFactory, boardFactory));


    /**
     * Set up clean instance of Factory and grid.
     */
    @BeforeEach
    void setUp() {
        grid = new ArrayList<>();
        grid.add("P #");
        grid.add("G .");
        when(boardFactory.createGround()).thenReturn(wall);
        when(boardFactory.createWall()).thenReturn(ground);
        when(levelFactory.createPellet()).thenReturn(pellet);
        when(levelFactory.createGhost()).thenReturn(ghost);


    }

    /**
     * Test Nice weather case that create Ground.
     */
    @Test
    void testNiceWeather() {
        final int five = 5;
        mapParser.parseMap(grid);
        verify(boardFactory, times(five)).createGround();
    }

    /**
     * Test Nice weather case that create wall.
     */
    @Test
    void testNiceWeather2() {
        mapParser.parseMap(grid);
        verify(boardFactory, times(1)).createWall();
    }

    /**
     * Test Nice weather case that create Ghost.
     */
    @Test
    void testNiceWeather3() {
        mapParser.parseMap(grid);
        verify(levelFactory, times(1)).createGhost();
    }

    /**
     * Test Nice weather case that create pellet.
     */
    @Test
    void testNiceWeather4() {
        mapParser.parseMap(grid);

        verify(levelFactory, times(1)).createPellet();
    }

    /**
     * Test Nice weather case that add player.
     */
    @Test
    void testNiceWeather5() {
        mapParser.addSquare(test, testGhost, testSquare, 0, 0, 'P');

        verify(boardFactory, times(1)).createGround();
    }

    /**
     * Test parseMap method for String mapName and InputStream source.
     *
     * @throws IOException for fileName instance.
     */
    @Test
    void testNiceWeather6() throws IOException {

        String fileName = "/simplemap.txt";

        mapParser.parseMap(fileName);

        verify(mapParser).parseMap(any(InputStream.class));
    }

    /**
     * Test for mapParser with bad weather situation.
     */
    @Test
    void testBadWeather1() {
        try {
            mapParser.parseMap(Lists.newArrayList("#", "  "));
        } catch (PacmanConfigurationException exception) {
            assertThat(exception.getMessage()).contains("Input text lines are not of equal width.");
        }
    }

    /**
     * Test for mapParser with empty input.
     */
    @Test
    void testBadWeather2() {
        try {
            mapParser.parseMap(Lists.newArrayList());
        } catch (PacmanConfigurationException exception) {
            assertThat(exception.getMessage())
                .contains("Input text must consist of at least 1 row.");
        }
    }

    /**
     * Test for mapParser with null input.
     */
    @Test
    void testBadWeather3() {
        List<String> test = null;
        try {
            mapParser.parseMap(test);
        } catch (PacmanConfigurationException exeception) {
            assertThat(exeception.getMessage()).contains("Input text cannot be null.");
        }
    }

    /**
     * Test for mapParser with null input.
     */
    @Test
    void testBadWeather4() {
        List<String> test = null;
        try {
            mapParser.parseMap(test);
        } catch (PacmanConfigurationException exeception) {
            assertThat(exeception.getMessage()).contains("Input text cannot be null.");
        }
    }

    /**
     * Test for mapParser with 0 width.
     */
    @Test
    void testBadWeather5() {
        try {
            mapParser.parseMap(Lists.newArrayList(""));
        }
        catch (PacmanConfigurationException e) {
            assertThat(e.getMessage()).contains("Input text lines cannot be empty.");
        }
    }

    /**
     * Test for invalid charactor.
     */
    @Test
    void testBadWeather6() {
        try {
            mapParser.addSquare(test, testGhost, testSquare, 1, 1, 'L');
        }
        catch (PacmanConfigurationException exception) {
            assertThat(exception.getMessage()).contains("Invalid character at "
                + 1 + "," + 1 + ": " + 'L');
        }
    }

    /**
     * Test for invalid charactor.
     */
    @Test
    void testBadWeather7() {
        try {
            mapParser.addSquare(test, testGhost, testSquare, 1, 1, 'L');
        }
        catch (PacmanConfigurationException exception) {
            assertThat(exception.getMessage()).contains("Invalid character at "
                + 1 + "," + 1 + ": " + 'L');
        }
    }

    /**
     * Test for null boardStream.
     * @throws IOException for filename
     */
    @Test
    void testBadWeather8() throws IOException {
        String fileName = "nullFileName";
        try {
            mapParser.parseMap(fileName);
        }
        catch (PacmanConfigurationException exception) {
            assertThat(exception.getMessage()).contains("Could not get resource for: "
                + fileName);
        }
    }

    /**
     *     Test getBoardCreator.
     */
    @Test
    void testgetBoardCreator() {
        BoardFactory test = mapParser.getBoardCreator();

        assertThat(test).isEqualTo(test);
    }


}
