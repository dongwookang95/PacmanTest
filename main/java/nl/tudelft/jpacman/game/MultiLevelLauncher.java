package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.ui.PacManUI;
import nl.tudelft.jpacman.ui.PacManUiBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Creates and launches the multi level JPacMan UI.
 */

public class MultiLevelLauncher extends Launcher {
    private PacManUI testPacman;
    private MultiLevelGame multiLevelGame;
    private PointCalculator pointCalculator = new DefaultPointCalculator();
    private PacManSprites pacManSprites = new PacManSprites();
    private PlayerFactory playerFactory = new PlayerFactory(pacManSprites);
    private String map1 = "/board3.txt";
    private String map2 = "/board3.txt";
    private String map3 = "/board3.txt";
    private String map4 = "/board3.txt";


    @Override
    public Launcher withMapFile(String fileName) {
        map1 = fileName;
        map2 = fileName;
        map3 = fileName;
        map4 = fileName;
        return this;
    }



    @Override
    public MultiLevelGame makeGame() {
        PointCalculator usingPointCalculator = pointCalculator;
        Player newPlayer = playerFactory.createPacMan();
        List<Level> levels = new ArrayList<>();
        levels.add(makeLevel(map1));
        levels.add(makeLevel(map2));
        levels.add(makeLevel(map3));
        levels.add(makeLevel(map4));
        multiLevelGame = new MultiLevelGame(usingPointCalculator,
            newPlayer, levels);

        return multiLevelGame;
    }

    private Level makeLevel(String s) {
        try {
            return getMapParser().parseMap(s);
        } catch (IOException e) {
            throw new PacmanConfigurationException(
                "input is not proper =  " + getLevelMap(), e);
        }
    }

    @Override
    public MultiLevelGame getGame() {
        return multiLevelGame;
    }

    @Override
    public void launch() {
        multiLevelGame = makeGame();
        PacManUiBuilder builder = new PacManUiBuilder().withDefaultButtons();
        addSinglePlayerKeys(builder);
        testPacman = builder.build(getGame());
        testPacman.start();
    }
}
