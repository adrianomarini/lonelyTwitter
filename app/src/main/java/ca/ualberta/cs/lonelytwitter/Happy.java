package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by marini on 9/16/15.
 */
public class Happy extends Mood {
    public Happy(Date date) {
        super(date);
        this.setMood();
    }

    public Happy() {
    }

    public void setMood(){
        this.mood = "-- Feeling Happy :)";
    }

    public String getMood(){
        return this.mood;
    }

}
