package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by marini on 9/16/15.
 */
public abstract class Tweet extends Object implements Tweetable {
    private String text;    //private: not accessible from outside the class; Protected: subclasses can access
    private Date date;
    private ArrayList<Mood> moodList = new ArrayList<Mood>();

    //"this" is a reference to the current class / object / chunk of code

    public Tweet(String text, Date date) throws IllegalArgumentException {
        this.setText(text);
        this.date = date;
    }

    public Tweet(String text) throws IllegalArgumentException {
        this.setText(text);
        this.date = new Date();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) throws IllegalArgumentException {
        if (text.length() <= 140) {
            this.text = text;
        } else {
            throw new IllegalArgumentException("Tweet was too long!");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract Boolean isImportant();

    public void addMood(String mood){
        if(mood.equals("happy")){
            moodList.add(new Happy());
        }
        if(mood.equals("sad")){
            moodList.add(new Sad());
        }
    }
}


