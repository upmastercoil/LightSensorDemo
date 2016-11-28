package tech.android.tcmp13.lightsensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private View shukiShade;

    private SensorManager sensorManager;
    private Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //UI
        setContentView(R.layout.activity_main);
        shukiShade = findViewById(R.id.shukiShade);

        //Sensor - get the sensor manager from the system
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //The list of all the light sensors on our device
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_LIGHT);
        for (Sensor sensor : sensorList) {
            Log.d("ttt", sensor.getName());
            Log.d("ttt", sensor.getVendor());
            Log.d("ttt", String.valueOf(sensor.getMaximumRange()));
            Log.d("ttt", String.valueOf(sensor.getMinDelay()));
        }

        //Get the light sensor that can be worked with
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {

        super.onResume();
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {

        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        //The value for the current level of LUX is in position 0 (there are 3)
        shukiShade.setAlpha(sensorEvent.values[0] / 500);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {


    }
}
