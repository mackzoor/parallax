package com.tda367.parallax.model.score;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that acts like a layer between LibGDX preferences and classes using stored score
 */

public class ScoreAdapter {

    Preferences preferences;

    //The top 10 highest scoring players, list not necessarily in order.
    private @Getter List<HighScoreHolder> highScoreHolders;


    public ScoreAdapter(){
        highScoreHolders = new ArrayList<HighScoreHolder>();
        preferences = Gdx.app.getPreferences("My Preferences");
        updateHighScoreHolders();
    }

    String temporaryString;

    //Creates a new list highest scores based on the preferences. Easier to handle.
    private void updateHighScoreHolders() {
        List<HighScoreHolder>tempList = new ArrayList<HighScoreHolder>();

        for (String s : preferences.get().keySet()) {
            temporaryString = s;
            Boolean check = false;
            while(!(check)) {
                check = removeNameDivider();
            }
            tempList.add(new HighScoreHolder(temporaryString,preferences.getInteger(s)));
        }
        highScoreHolders = tempList;
        sortHighScoreHolders();
    }
    private boolean removeNameDivider(){
        if(temporaryString.endsWith("#")){
            StringBuilder sb = new StringBuilder(temporaryString);

            sb.deleteCharAt(sb.length()-1);

            temporaryString = sb.toString();
            return false;
        }else {
            return true;
        }
    }


    //Sort the updateHighScoreHolder list. Making it be in order of highest score, to the 10th highest score.
    private void sortHighScoreHolders(){

        ArrayList sortedHighScores = new ArrayList<HighScoreHolder>();
        HighScoreHolder currentHighestScore = new HighScoreHolder("No highest scorers", -1);

        for (int i = 0; i < highScoreHolders.size(); i++){
            for(int j= 0; j < highScoreHolders.size(); j++){
                if(highScoreHolders.get(j).getScore() > currentHighestScore.getScore()) {
                    if(!(sortedHighScores.contains(highScoreHolders.get(j)))){
                        currentHighestScore = highScoreHolders.get(j);
                    }
                }
            }
            sortedHighScores.add(currentHighestScore);
            currentHighestScore = new HighScoreHolder(null, -2);
        }

        highScoreHolders = new ArrayList<HighScoreHolder>(sortedHighScores);
    }


    //Store a score if it is amongst the top 10 scores.
    public void storeHighScore(String name, int score){
        //Check if there is 10 power-ups
        if(!(preferences.get().size() < 10)) {

            //make a suitable name for storage
            String storeName = suitableName(name);

            //Place in preferences
            preferences.putInteger(storeName, score);

            preferences.flush();

            //Remove least
            removeLowestHighscore();

            } else{
            String storeName = suitableName(name);

            preferences.putInteger(storeName, score);
        }
        preferences.flush();
        updateHighScoreHolders();
    }
    private String suitableName(String name){

        String returnName = name;
        boolean nameAvailable = false;
        while(!(nameAvailable)){
            nameAvailable = checkAvailability(returnName);
            if(nameAvailable == false){
                StringBuilder sb = new StringBuilder(returnName);
                sb.append("#");
                returnName = sb.toString();
            }
        }

        return returnName;
    }
    private void removeLowestHighscore(){
        String removeName = null;
        int lowestScore = -1;
        for (String s : preferences.get().keySet()) {
            if(lowestScore == -1){
                lowestScore = preferences.getInteger(s);
                removeName = s;
            }else{
                if(preferences.getInteger(s) < lowestScore || removeName==null){
                    removeName = s;
                    lowestScore = preferences.getInteger(s);
                }
            }
        }
        if(removeName != null){
        preferences.remove(removeName);
        }
    }
    private boolean checkAvailability(String name){
        for (String s : preferences.get().keySet()) {
            if(s.equals(name)){
                return false;
            }
        }
        return true;
    }


    //Get highest scores in list, in two different ways
    public List<Integer> getHighScores() {
        ArrayList<Integer> highScore = new ArrayList<Integer>();
        for(HighScoreHolder holder: highScoreHolders){
            highScore.add(new Integer(holder.getScore()));
        }
        return highScore;
    }
    public List<String> getHighScoresHolders(){
        ArrayList<String> highScoreName = new ArrayList<String>();
        for(HighScoreHolder holder: highScoreHolders){
            highScoreName.add(new String(holder.getName()));
        }
        return highScoreName;
    }
    public void removeAllStoredHighScores(){
        preferences.clear();
        preferences.flush();
    }
}
