package ca.ualberta.cs.lonelytwitter;

/**
 * Created by marini on 10/14/15.
 */
public class TweetBeingEdited {
    private static TweetBeingEdited ourInstance = new TweetBeingEdited();

    public static TweetBeingEdited getInstance() {
        return ourInstance;
    }

    private TweetBeingEdited() {
    }
}
