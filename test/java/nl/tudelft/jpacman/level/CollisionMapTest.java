package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.mockito.Mockito.times;

/**
 * Test suite that tests different forms of Collisions.
 */
public abstract class CollisionMapTest {

    private Ghost ghost = Mockito.mock(Ghost.class);
    private Player player = Mockito.mock(Player.class);
    private Pellet pellet = Mockito.mock(Pellet.class);
    private Unit unit = Mockito.mock(Unit.class);
    private CollisionMap collisionMap = null;
    private DefaultPointCalculator defaultPointCalculator =
        Mockito.mock(DefaultPointCalculator.class);

    /**
     * @param collisionMap Type of CollisionMap to be set.
     */
    public void setCollisionMap(CollisionMap collisionMap) {
        this.collisionMap = collisionMap;
    }

    /**
     * Method to allow other classes to use this class with their own CollsionMap.
     */
    public abstract void setCollisionMap();

    /**
     * Test if the player dies when it collides with a ghost, ghost into player.
     */
    @Test
    void ghostPlayerCollision() {
        collisionMap.collide(ghost, player);
        Mockito.verify(player).setAlive(false);
    }

    /**
     * Test if the player dies when it collides with a ghost, player into ghost (Reverse).
     */
    @Test
    void playerGhostCollision() {
        collisionMap.collide(player, ghost);
        Mockito.verify(player).setAlive(false);
    }

    /**
     * Test if the pellet disappears when colliding with the player.
     */
    @Test
    void pelletPlayerCollision() {
        collisionMap.collide(pellet, player);
        Mockito.verify(pellet).leaveSquare();
        Mockito.verify(player).addPoints(pellet.getValue());
    }

    /**
     * Test if the pellet disappears when colliding with the player (Reverse).
     */
    @Test
    void playerPelletCollision() {
        collisionMap.collide(player, pellet);
        Mockito.verify(pellet).leaveSquare();
        Mockito.verify(player).addPoints(pellet.getValue());
    }

    /**
     * Test when two units collide.
     */
    @Test
    void unitUnitCollision() {
        collisionMap.collide(unit, unit);
        Mockito.verify(defaultPointCalculator, times(0)).consumedAPellet(player, pellet);
        Mockito.verify(player, times(0)).setAlive(false);
        Mockito.verify(pellet, times(0)).leaveSquare();
        Mockito.verify(ghost, times(0)).leaveSquare();
    }

    /**
     * Test when a pellet collides with any unit.
     */
    @Test
    void pelletUnitCollision() {
        collisionMap.collide(pellet, unit);
        Mockito.verify(defaultPointCalculator, times(0)).consumedAPellet(player, pellet);
        Mockito.verify(player, times(0)).setAlive(false);
        Mockito.verify(pellet, times(0)).leaveSquare();
        Mockito.verify(ghost, times(0)).leaveSquare();
    }

}
