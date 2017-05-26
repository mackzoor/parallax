package com.tda367.parallax.model.score;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that acts like a layer between LibGDX preferences and classes using stored score
 */

public class ScoreHandler {

    private static final int LENGTH_OF_LIST = 10;

    //Storage file connected to LibGDX storage.
    private Preferences preferences;

    //The top 10 highest scoring players, list not necessarily in order.
    @Getter private List<HighScoreHolder> highScoreHolders;

    private String temporaryString;


    public ScoreHandler() {
        this.highScoreHolders = new ArrayList<HighScoreHolder>();
        this.preferences = Gdx.app.getPreferences("My Preferences");
        updateHighScoreHolders();
    }

    //Creates a new list highest scores based on the preferences. Easier to handle.
    private void updateHighScoreHolders() {
        final List<HighScoreHolder> tempList = new ArrayList<HighScoreHolder>();

        for (final String preferenceName : this.preferences.get().keySet()) {
            this.temporaryString = preferenceName;
            Boolean check = false;
            while (!check) {
                check = removeNameDivider();
            }
            tempList.add(new HighScoreHolder(this.temporaryString, this.preferences.getInteger(preferenceName)));
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


    //Sort the updateHighScoreHolder list. Making it be in order of highest score, to the 10th highest score.
    private void sortHighScoreHolders() {

        final ArrayList sortedHighScores = new ArrayList<HighScoreHolder>();
        HighScoreHolder currentHighestScore = new HighScoreHolder("No highest scorers", -1);

        for (int i = 0; i < this.highScoreHolders.size(); i++) {
            for (int j = 0; j < this.highScoreHolders.size(); j++) {
                if (this.highScoreHolders.get(j).getScore() > currentHighestScore.getScore() &&
                        !(sortedHighScores.contains(this.highScoreHolders.get(j)))) {
                    currentHighestScore = this.highScoreHolders.get(j);
                }
            }
            sortedHighScores.add(currentHighestScore);
            currentHighestScore = new HighScoreHolder(null, -2);
        }

        this.highScoreHolders = new ArrayList<HighScoreHolder>(sortedHighScores);
    }


    //Store a score if it is amongst the top 10 scores. Adds score, then removes lowest.
    public void storeHighScore(String name, int score) {
        if (!(this.preferences.get().size() < LENGTH_OF_LIST)) {

            final String storeName = suitableName(name);

            this.preferences.putInteger(storeName, score);

            this.preferences.flush();

            removeLowestHighScore();

        } else {
            final String storeName = suitableName(name);

            this.preferences.putInteger(storeName, score);
        }
        this.preferences.flush();
        this.updateHighScoreHolders();
    }

    private String suitableName(String name) {
        //Method that adds an # if the name already exists amongst the top 10 score holders.
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
        for (final String preferenceName : this.preferences.get().keySet()) {
            if (lowestScore == -1) {
                lowestScore = this.preferences.getInteger(preferenceName);
                removeName = preferenceName;
            } else {
                if (this.preferences.getInteger(preferenceName) < lowestScore || removeName == null) {
                    removeName = preferenceName;
                    lowestScore = this.preferences.getInteger(preferenceName);
                }
            }
        }
        if (removeName != null) {
            this.preferences.remove(removeName);
        }
    }

    private boolean checkAvailability(String name) {
        for (final String preferenceName : this.preferences.get().keySet()) {
            if (preferenceName.equals(name)) {
                return false;
            }
        }
        return true;
    }


    //Get highest scores in list, in two different ways
    public List<Integer> getHighScores() {
        final ArrayList<Integer> highScore = new ArrayList<Integer>();
        for (final HighScoreHolder holder : this.highScoreHolders) {
            highScore.add(holder.getScore());
        }
        return highScore;
    }

    public List<String> getHighScoresHolders() {
        final ArrayList<String> highScoreName = new ArrayList<String>();
        for (final HighScoreHolder holder : this.highScoreHolders) {
            highScoreName.add(holder.getName());
        }
        return highScoreName;
    }


    public void removeAllStoredHighScores() {
        this.preferences.clear();
        this.preferences.flush();
    }
}
