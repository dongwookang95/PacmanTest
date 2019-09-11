package nl.tudelft.jpacman.integration.suspension;


import nl.tudelft.jpacman.Launcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Scenario S4.1: Suspend the game.
 **/
public class SuspendGameTest {


    private Launcher launcherTest;


    /**
     * initialise the game.
     */
    @BeforeEach
    public void initialise() {
        launcherTest = new Launcher();
    }

    /**
     * Given the game has started.
     */


    @Test
    public void gameStartTest() {
        launcherTest.launch();
        launcherTest.getGame().start();

        assertThat(launcherTest.getGame().isInProgress()).isTrue();
    }

    /**
     * When  the player clicks the "Stop" button.
     */
    @Test
    public void isStopped() {
        launcherTest.launch();
        launcherTest.getGame().start();
        launcherTest.getGame().stop();

        assertThat(launcherTest.getGame().isInProgress()).isFalse();

    }

}
