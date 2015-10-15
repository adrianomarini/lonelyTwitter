package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private Button saveButton;
    private EditText bodyText;
    private EditText editTweet;
    private Button saveEdits;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    public void testEditTweet(){
        //when you call a get activity, the system will start the app and the activity
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();

        //reset the app to a known state
        activity.getTweets().clear();

        //add a tweet using the UI
        bodyText = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("testTweet");
            }
        });
        getInstrumentation().waitForIdleSync();

        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        // make sure the tweet was actually added
        final ListView oldTweetsList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("testTweet", tweet.getText());

        // ensure the tweet editor activity starts up
        //https://developer.android.com/training/activity-testing/activity-functional-testing.html 2015-0-13
        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);
        //click on the tweet to edit
        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0 , v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();
        // Validate that ReceiverActivity is started
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

        //test that the editor starts up with the right tweet
        assertEquals(tweet, receiverActivity.getCurrentTweet());

        //test that we can edit that tweet
        editTweet = receiverActivity.getTweetField();
        receiverActivity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("I Edited This!");
            }
        });
        getInstrumentation().waitForIdleSync();

        //test that we can push some sort of save button for that tweet
        saveEdits = receiverActivity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveEdits.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        //clean up activities at the end of our test
        receiverActivity.finish();

        //the new modified tweet text was actually saved and is displayed
        Tweet newTweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("I Edited This!", newTweet.getText());

    }
}