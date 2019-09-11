package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.points.DefaultPointCalculator;
import org.junit.jupiter.api.BeforeEach;

/**
 * Simple initiation so that the PlayerCollisionTest also
 * works on the CollisionMapTest.
 */
class PlayerCollisionsTest extends CollisionMapTest {

    private CollisionMap collisionMap = new PlayerCollisions(new DefaultPointCalculator());

    @Override
    public void setCollisionMap() {
        setCollisionMap(collisionMap);
    }

    /**
     * Set up the CollisionMap in the CollisionMapTest to an instance of the PlayerCollisionTest.
     */
    @BeforeEach
    public void setUp() {
        setCollisionMap();
    }

}
