package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by marini on 9/16/15.
 */
public class Sad extends Mood {
    public Sad(Date date) {
        super(date);
        this.setMood();
    }

    public Sad() {
    }

    public void setMood(){
        this.mood = "-- Feeling Sad :(";
    }

    public String getMood(){
        return this.mood;
    }
}
