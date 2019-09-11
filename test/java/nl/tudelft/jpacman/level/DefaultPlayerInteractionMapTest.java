package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;

/**
 * Simple initiation so that the DefaultPlayerInteractionMapTest also
 * works on the CollisionsMapTest.
 */
public class DefaultPlayerInteractionMapTest extends CollisionMapTest {

    private PointCalculator pointCalculator = new DefaultPointCalculator();
    private CollisionMap collisionMap = new DefaultPlayerInteractionMap(pointCalculator);


    @Override
    public void setCollisionMap() {
        setCollisionMap(collisionMap);
    }

    /**
     * Set up the CollisionMap in the CollisionMapTest to an instance of
     * the DefaultPlayerInteractionMap.
     */
    @BeforeEach
    public void setUp() {
        setCollisionMap();
    }

}
