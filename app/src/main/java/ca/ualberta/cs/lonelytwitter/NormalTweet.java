package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by marini on 9/16/15.
 */
public class NormalTweet extends Tweet{

    public NormalTweet(String text, Date date) {super(text, date);
    }

    public NormalTweet(String text) {
        super(text);
    }

    public Boolean isImporant(){
        return Boolean.FALSE;
    }
}
