package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by marini on 9/16/15.
 */
public abstract class Mood extends Object{
    private Date date;
    protected String mood;

    public Mood(Date date) {
        this.date = date;
    }

    public Mood() {
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract void setMood();

    public abstract String getMood();
}
