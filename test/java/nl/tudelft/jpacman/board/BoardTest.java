package nl.tudelft.jpacman.board;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test board class.
 */
class BoardTest {



    private Board board;
    private Board boardTest;
    private Square[][] grid;
    private Square[][] gridTest;


    /**
     * Set up a grid with 1x1 board.
     */
    @BeforeEach
    void setBoard() {
        final int four = 4;
        grid = new BasicSquare[1][1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new BasicSquare();
            }
        }
        board = new Board(grid);

        gridTest = new BasicSquare[four][four];
        for (int i = 0; i < gridTest.length; i++) {
            for (int j = 0; j < gridTest[i].length; j++) {
                gridTest[i][j] = new BasicSquare();
            }
        }
        boardTest = new Board(gridTest);
    }

    /**
     * Test invariant method.
     */
    @Test
    void invariant() {
        Square test = new BasicSquare();
        boolean result = test.invariant();
        assertThat(result).isEqualTo(true);

    }

    /**
     * Test getWidth method.
     */
    @Test
    void getWidth() {
        int result = board.getWidth();
        assertEquals(1, result);
    }

    /**
     * Test getHeight method.
     */
    @Test
    void getHeight() {
        int result = board.getHeight();
        assertEquals(1, result);
    }

    /**
     * Test squareAt method.
     */
    @Test
    void squareAt() {
        Square result = board.squareAt(0, 0);
        assertThat(result).isEqualTo(grid[0][0]);
    }

//    /**
//     * Test squareAtNull method.
//     */
//    @Test
//    void squareAtNull() {
//        Square[][] test = new BasicSquare[1][1];
//        Board testBoard = new Board(test);
//        assertThat(testBoard.squareAt(0, 0)).isNull();
//    }

    /**
     * Test withinBoarders method.
     * provide domain matrix with CsvSource
     * test is executed as ParameterizedTest with CsvSource instead of normal test
     * @param x represent horizontal coordinate
     * @param y represent vertical grid
     */
    @ParameterizedTest
    @CsvSource({
        "0,0", "1,0",
        "1,1", "0,1",
        "2,0", "2,1",
        "2,2", "1,2",
        "0,2", "3,0",
        "3,1", "3,2",
        "3,3"
    })

    /**
     * Test withinBoarders method within board.
     * provide domain matrix with CsvSource
     * test is executed as ParameterizedTest with CsvSource instead of normal test
     * @param x represent horizontal coordinate
     * @param y represent vertical grid
     */
    void withinBordersTrue(int x, int y) {
        Assertions.assertTrue(boardTest.withinBorders(x, y));
    }


    /**
     * Test withinBoarders method out of board.
     * provide domain matrix with CsvSource
     * test is executed as ParameterizedTest with CsvSource instead of normal test
     * @param x represent horizontal coordinate
     * @param y represent vertical grid
     */
    @ParameterizedTest
    @CsvSource({
        "4,0",
        "4,1", "4,2",
        "4,3", "4,4",
        "5,0", "5,1",
        "5,2", "5,3",
        "5,4", "5,5"
    })
    void withinBordersFalse(int x, int y) {
        Assertions.assertFalse(boardTest.withinBorders(x, y));
    }
}

