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

    private final static int LENGTH_OF_LIST = 10;

    //Storage file connected to LibGDX storage.
    private Preferences preferences;

    //The top 10 highest scoring players, list not necessarily in order.
    private @Getter
    List<HighScoreHolder> highScoreHolders;

    private String temporaryString;



    public ScoreHandler() {
        this.highScoreHolders = new ArrayList<HighScoreHolder>();
        this.preferences = Gdx.app.getPreferences("My Preferences");
        updateHighScoreHolders();
    }

    //Creates a new list highest scores based on the preferences. Easier to handle.
    private void updateHighScoreHolders() {
        List<HighScoreHolder> tempList = new ArrayList<HighScoreHolder>();

        for (String s : this.preferences.get().keySet()) {
            this.temporaryString = s;
            Boolean check = false;
            while (!(check)) {
                check = removeNameDivider();
            }
            tempList.add(new HighScoreHolder(this.temporaryString, this.preferences.getInteger(s)));
        }
        this.highScoreHolders = tempList;
        sortHighScoreHolders();
    }

    private boolean removeNameDivider() {
        if (this.temporaryString.endsWith("#")) {
            StringBuilder sb = new StringBuilder(this.temporaryString);

            sb.deleteCharAt(sb.length() - 1);

            this.temporaryString = sb.toString();
            return false;
        } else {
            return true;
        }
    }


    //Sort the updateHighScoreHolder list. Making it be in order of highest score, to the 10th highest score.
    private void sortHighScoreHolders() {

        ArrayList sortedHighScores = new ArrayList<HighScoreHolder>();
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

            String storeName = suitableName(name);

            this.preferences.putInteger(storeName, score);

            this.preferences.flush();

            removeLowestHighScore();

        } else {
            String storeName = suitableName(name);

            this.preferences.putInteger(storeName, score);
        }
        this.preferences.flush();
        this.updateHighScoreHolders();
    }

    private String suitableName(String name) {
        //Method that adds an # if the name already exists amongst the top 10 score holders.
        String returnName = name;
        boolean nameAvailable = false;
        while (!(nameAvailable)) {
            nameAvailable = checkAvailability(returnName);
            if (!nameAvailable) {
                StringBuilder sb = new StringBuilder(returnName);
                sb.append("#");
                returnName = sb.toString();
            }
        }
        return returnName;
    }

    private void removeLowestHighScore() {
        String removeName = null;
        int lowestScore = -1;
        for (String s : this.preferences.get().keySet()) {
            if (lowestScore == -1) {
                lowestScore = this.preferences.getInteger(s);
                removeName = s;
            } else {
                if (this.preferences.getInteger(s) < lowestScore || removeName == null) {
                    removeName = s;
                    lowestScore = this.preferences.getInteger(s);
                }
            }
        }
        if (removeName != null) {
            this.preferences.remove(removeName);
        }
    }

    private boolean checkAvailability(String name) {
        for (String s : this.preferences.get().keySet()) {
            if (s.equals(name)) {
                return false;
            }
        }
        return true;
    }


    //Get highest scores in list, in two different ways
    public List<Integer> getHighScores() {
        ArrayList<Integer> highScore = new ArrayList<Integer>();
        for (HighScoreHolder holder : this.highScoreHolders) {
            highScore.add(holder.getScore());
        }
        return highScore;
    }

    public List<String> getHighScoresHolders() {
        ArrayList<String> highScoreName = new ArrayList<String>();
        for (HighScoreHolder holder : this.highScoreHolders) {
            highScoreName.add(holder.getName());
        }
        return highScoreName;
    }


    public void removeAllStoredHighScores() {
        this.preferences.clear();
        this.preferences.flush();
    }
}
