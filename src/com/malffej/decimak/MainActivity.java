package com.malffej.decimak;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.malffej.decimak.ShakeDetector.OnShakeListener;

public class MainActivity extends Activity {
	
	private DecisionMaker mDecisionMaker = new DecisionMaker();
	private TextView mAnswerLabel;
	private Button mButton;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Assign Views from the layout file
        mAnswerLabel = (TextView) findViewById(R.id.textView1);
        mButton = (Button) findViewById(R.id.button1);
        
        //Assigning Sensor variables
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new OnShakeListener() {
			@Override
			public void onShake() {
				handleNewAnswer();
			}
		});
        
        //Button press
        mButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				handleNewAnswer();
			}
		});
    }

    
    @Override
    public void onResume(){
    	super.onResume();
    	mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    	mSensorManager.unregisterListener(mShakeDetector);
    }
    
    public void onSwitchClicked(View view){
    	//Is toggle on?
    	boolean on = ((Switch) view).isChecked();
    	
    	if (on){
    		Toast.makeText(this, "The switch is on!", Toast.LENGTH_LONG).show();
    		mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    	}else{
    		Toast.makeText(this, "The switch is off!", Toast.LENGTH_LONG).show();
    		mSensorManager.unregisterListener(mShakeDetector);
    	}
    }
    
  
    private void handleNewAnswer(){
    	//Executes DecisionMaker class
    	String answer = mDecisionMaker.getAnswer();
    	//Updates mAnswerLabel with the answer
    	mAnswerLabel.setText(answer);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
