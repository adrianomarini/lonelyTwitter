package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav"; //model
	private EditText bodyText; //view
	private ListView oldTweetsList; //view
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>(); //view
    private ArrayAdapter<Tweet> adapter; //view

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState); //controller
		setContentView(R.layout.main); //view

		bodyText = (EditText) findViewById(R.id.body); //view
		Button saveButton = (Button) findViewById(R.id.save); //view
        Button clearButton= (Button) findViewById(R.id.clear); //view
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList); //view

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK); //controller
				String text = bodyText.getText().toString(); //model
                tweets.add(new NormalTweet(text)); //controller
				saveInFile(); //model
				//dataObject.saveInFile() ----> controller
                adapter.notifyDataSetChanged(); //view
			}
		});

        clearButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                setResult(RESULT_OK); //controller
                clearFile(); //model
                adapter.notifyDataSetChanged(); //view
            }
        });
	}

	@Override
	protected void onStart() {
		super.onStart(); //controller
        loadFromFile(); //model
		adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweets); //view
		oldTweetsList.setAdapter(adapter); //controller
        adapter.notifyDataSetChanged(); //view
	}

	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME); //model
			BufferedReader in = new BufferedReader(new InputStreamReader(fis)); //model
            Gson gson = new Gson(); //model
            //http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type arrayListType = new TypeToken<ArrayList<NormalTweet>>() {}.getType(); //model
            tweets = gson.fromJson(in, arrayListType); //controller
		} catch (FileNotFoundException e) {
			tweets = new ArrayList<Tweet>(); //model
		}
	}
	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0); //model
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos)); //model
            Gson gson = new Gson(); //model
            gson.toJson(tweets, out); //controller
            out.flush(); //controller
			fos.close(); //controller
		} catch (FileNotFoundException e) {
			tweets = new ArrayList<Tweet>(); //model
		} catch (IOException e) {
            throw new RuntimeException(e); //model
		}
	}

    private void clearFile(){
        tweets.clear(); //controller
        saveInFile(); //model
    }
}