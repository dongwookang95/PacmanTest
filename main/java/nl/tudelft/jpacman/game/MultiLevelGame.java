package nl.tudelft.jpacman.game;

import com.google.common.collect.ImmutableList;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * MultiLevelGame that extends Game class.
 */
public class MultiLevelGame extends Game {

    private int numberLevel = 1;
    private Player player;
    private List<Level> levelList = new ArrayList<>();


    /**
     * A constructor for multiLevelGame.
     * @param player Player.
     * @param levelList List of levels.
     * @param pointCalculator Calculator for points.
     */
    public MultiLevelGame(PointCalculator pointCalculator, Player player,   List<Level> levelList) {
        super(pointCalculator);
        this.player = player;
        this.levelList = levelList;
        this.numberLevel = 1;
        getLevel().registerPlayer(player);
    }

    @Override
    public List<Player> getPlayers() {
        return ImmutableList.of(player);
    }

    @Override
    public Level getLevel() {
        return levelList.get(numberLevel - 1);
    }

    @Override
    public void currentLevel() {

    }

    @Override
    public void levelWon() {
        if (numberLevel < 2 + 2) {
            stop();
            this.numberLevel = this.numberLevel + 1;
            getLevel().registerPlayer(player);

            return;
        } else {
            stop();
        }
    }

    @Override
    public void levelLost() {
        if (!player.isAlive() && getLevel().remainingPellets() != 0) {
            stop();
            player.setAlive(false);
            getLevel().stop();
        } else if (numberLevel == 2 + 1 + 1) {
            stop();
        }
    }

    @Override
    public void gameWon() { }

    @Override
    public void gameLost() { }

}
