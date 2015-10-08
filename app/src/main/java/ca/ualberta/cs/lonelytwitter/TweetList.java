package ca.ualberta.cs.lonelytwitter;

import android.database.AbstractWindowedCursor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by marini on 9/30/15.
 */
public class TweetList implements MyObservable {
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    private ArrayList<MyObserver> myObservers = new ArrayList<MyObserver>();

    public void add(Tweet tweet) throws IllegalArgumentException{
        tweets.add(tweet);
        notifyObservers();
    }

    public void removeTweet(Tweet tweet){
        tweets.remove(tweet);
    }

    public boolean hasTweet(Tweet tweet){
        return tweets.contains(tweet);
    }

    public Tweet getTweet(int index){
        return tweets.get(index);
    }

    public ArrayList<Tweet> getTweets() {
        ArrayList<Tweet> orderedTweets = new ArrayList<Tweet>();
        Tweet tempTweet;
        Date tempDate;
        for (int i = 0; i < tweets.size(); i++) {
            tempTweet = tweets.get(i);
            tempDate = tempTweet.getDate();
            if (tempDate.before(orderedTweets.get(0).getDate())) {
                orderedTweets.add(0, tempTweet);
                break;
            }
            for (int j = 0; i < orderedTweets.size(); i++) {
                if (tempDate.after(orderedTweets.get(j).getDate())) {
                    orderedTweets.add(j + 1, tempTweet);
                    break;
                }
            }
        }
        return orderedTweets;
    }

    public int getCount(){
        return tweets.size();
    }

    public void clear(){
        tweets.clear();
    }


    public void addObserver(MyObserver o) {
        myObservers.add(o);
    }

    public void notifyObservers() {
        for(MyObserver observer : myObservers){
            observer.myNotify();
        }
    }
}
