package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by marini on 9/16/15.
 */
public class ImportantTweet extends Tweet{
    public ImportantTweet(String text) {
        super(text);
        this.setText(text);
    }

    public ImportantTweet(String text, Date date) {
        super(text, date);
    }

    public Boolean isImportant(){
        return Boolean.TRUE;
    }

    @Override
    public String getText(){
        return "!!!" + super.getText();
    }
}
