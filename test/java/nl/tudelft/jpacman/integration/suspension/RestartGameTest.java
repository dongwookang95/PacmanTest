package nl.tudelft.jpacman.integration.suspension;

import nl.tudelft.jpacman.Launcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Scenario S4.2: Restart the game.
 */
public class RestartGameTest {



    private Launcher launcherTest;


    /**
     * initialise the game.
     */
    @BeforeEach
    public void initialise() {
        launcherTest = new Launcher();
    }

    /**
     * When the player clicks the "Stop" button.
     */
    @Test
    public void gameStopped() {
        launcherTest.launch();
        launcherTest.getGame().start();
        launcherTest.getGame().stop();

        assertThat(launcherTest.getGame().isInProgress()).isFalse();

    }

    /**
     * When the player clicks the "Start" button after game is paused.
     */
    @Test
    public void gameResumed() {
        launcherTest.launch();
        launcherTest.getGame().start();
        launcherTest.getGame().stop();
        //game is stopped.
        launcherTest.getGame().start();
        //game is started again.

        assertThat(launcherTest.getGame().isInProgress()).isTrue();


    }
}
