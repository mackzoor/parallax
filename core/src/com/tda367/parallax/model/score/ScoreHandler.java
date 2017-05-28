package com.tda367.parallax.model.score;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that acts like a layer between LibGDX storageLocation and classes using stored score.
 */

public class ScoreHandler {

    private static final int LENGTH_OF_LIST = 10;

    private final Preferences storageLocation;

    @Getter
    private List<HighScoreHolder> highScoreHolders;

    private String temporaryString;


    public ScoreHandler() {
        this.highScoreHolders = new ArrayList<HighScoreHolder>();
        this.storageLocation = Gdx.app.getPreferences("My Preferences.txt");
        updateHighScoreHolders();
    }

    private void updateHighScoreHolders() {
        final List<HighScoreHolder> tempList = new ArrayList<HighScoreHolder>();

        for (final String preferenceName : this.storageLocation.get().keySet()) {
            this.temporaryString = preferenceName;
            Boolean check = false;
            while (!check) {
                check = removeNameDivider();
            }
            tempList.add(new HighScoreHolder(this.temporaryString, this.storageLocation.getInteger(preferenceName)));
        }
        this.highScoreHolders = tempList;
        sortHighScoreHolders();
    }

    private boolean removeNameDivider() {
        if (this.temporaryString.endsWith("#")) {
            final StringBuilder builder = new StringBuilder(this.temporaryString);

            builder.deleteCharAt(builder.length() - 1);

            this.temporaryString = builder.toString();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Sorts highscore holders list from highest score to lowest.
     */
    private void sortHighScoreHolders() {

        final List sortedHighScores = new ArrayList<HighScoreHolder>();
        HighScoreHolder currentHighestScore = new HighScoreHolder("No highest scorers", -1);

        for (int i = 0; i < this.highScoreHolders.size(); i++) {
            for (int j = 0; j < this.highScoreHolders.size(); j++) {
                if (this.highScoreHolders.get(j).getScore() > currentHighestScore.getScore()
                        && !(sortedHighScores.contains(this.highScoreHolders.get(j)))) {
                    currentHighestScore = this.highScoreHolders.get(j);
                }
            }
            sortedHighScores.add(currentHighestScore);
            currentHighestScore = new HighScoreHolder(null, -2);
        }

        this.highScoreHolders = new ArrayList<HighScoreHolder>(sortedHighScores);
    }


    public void storeHighScore(String name, int score) {
        if (this.storageLocation.get().size() < LENGTH_OF_LIST) {

            final String storeName = suitableName(name);

            this.storageLocation.putInteger(storeName, score);

        } else {
            final String storeName = suitableName(name);

            this.storageLocation.putInteger(storeName, score);

            this.storageLocation.flush();

            removeLowestHighScore();
        }
        this.storageLocation.flush();
        this.updateHighScoreHolders();
    }

    /**
     * Adds an # if the name already exists amongst the top 10 score holders.
     */
    private String suitableName(String name) {
        String returnName = name;
        boolean nameAvailable = false;
        while (!nameAvailable) {
            nameAvailable = checkAvailability(returnName);
            if (!nameAvailable) {
                final StringBuilder builder = new StringBuilder(returnName);
                builder.append("#");
                returnName = builder.toString();
            }
        }
        return returnName;
    }

    private void removeLowestHighScore() {
        String removeName = null;
        int lowestScore = -1;
        for (final String preferenceName : this.storageLocation.get().keySet()) {
            if (lowestScore == -1) {
                lowestScore = this.storageLocation.getInteger(preferenceName);
                removeName = preferenceName;
            } else {
                if (this.storageLocation.getInteger(preferenceName) < lowestScore || removeName == null) {
                    removeName = preferenceName;
                    lowestScore = this.storageLocation.getInteger(preferenceName);
                }
            }
        }
        if (removeName != null) {
            this.storageLocation.remove(removeName);
        }
    }

    private boolean checkAvailability(String name) {
        for (final String preferenceName : this.storageLocation.get().keySet()) {
            if (preferenceName.equals(name)) {
                return false;
            }
        }
        return true;
    }


    public List<Integer> getHighScores() {
        final List<Integer> highScore = new ArrayList<Integer>();
        for (final HighScoreHolder holder : this.highScoreHolders) {
            highScore.add(holder.getScore());
        }
        return highScore;
    }

    public List<String> getHighScoresHolders() {
        final List<String> highScoreName = new ArrayList<String>();
        for (final HighScoreHolder holder : this.highScoreHolders) {
            highScoreName.add(holder.getName());
        }
        return highScoreName;
    }


    public void removeAllStoredHighScores() {
        this.storageLocation.clear();
        this.storageLocation.flush();
    }
}
