package ca.ualberta.cs.lonelytwitter.test;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import java.util.ArrayList;

import ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity;
import ca.ualberta.cs.lonelytwitter.NormalTweet;
import ca.ualberta.cs.lonelytwitter.Tweet;
import ca.ualberta.cs.lonelytwitter.TweetList;

/**
 * Created by marini on 9/30/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {
    public TweetListTest() {
        super(LonelyTwitterActivity.class);
    }

    @Override
    public void setUp() throws Exception{
        super.setUp();
    }

    //public something myHelperForTesting()

    public int testRemoveTweet() throws AssertionFailedError{
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HIHIHIHIHI");
        tweetList.add(tweet);
        tweetList.removeTweet(tweet);
        assertFalse(tweetList.hasTweet(tweet));
        try{
            tweetList.add(tweet);
        }
        catch(IllegalArgumentException e){
            return 0;
        }
        throw new AssertionFailedError();
    }

    public void testHasTweet(){
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HIHIHIHIHI");
        assertFalse(tweetList.hasTweet(tweet));
        tweetList.add(tweet);
        assertTrue(tweetList.hasTweet(tweet));
    }

    public void testAddTweet(){
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HIHIHIHIHI");
        tweetList.add(tweet);
    }

    public void testTweetCount(){
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HIHIHIHIHI");
        tweetList.add(tweet);
        assertTrue(tweetList.getCount() == 1);
        Tweet twitters = new NormalTweet("BYEFELICIA");
        tweetList.add(twitters);
        assertTrue(tweetList.getCount()==2);
    }

    public void testGetTweet(){
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HIHIHIHIHI");
        tweetList.add(tweet);
        Tweet returnedTweet = tweetList.getTweet(0);
        assertTrue((tweet.getDate().equals(returnedTweet.getDate())) && tweet.getText().equals(returnedTweet.getText()));
    }

    public void testGetTweets(){
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HIHIHIHIHI");
        tweetList.add(tweet);
        Tweet tweet2 = new NormalTweet("BYEBYEBYE");
        tweetList.add(tweet2);
        ArrayList<Tweet> orderedTweets = new ArrayList<Tweet>();
        orderedTweets = tweetList.getTweets();
        assertTrue(orderedTweets.get(0).getDate().before(orderedTweets.get(1).getDate()));
    }


}