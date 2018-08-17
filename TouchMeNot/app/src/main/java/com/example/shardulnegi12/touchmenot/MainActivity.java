package com.example.shardulnegi12.touchmenot;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    ImageView imageView;
    SensorManager sm;

    Sensor proximitySensor, LightSensor, accelerometerSensor;

    int[] imageId = new int[69];

    int currentImage = 68;

    Handler handlerNear = new Handler();
    Handler handlerFar = new Handler();

    Runnable runNear = new Runnable() {
        @Override
        public void run() {
            if (currentImage > 0) {
                imageView.setImageResource(imageId[--currentImage]);
                handlerNear.postDelayed(runNear, 30);
            }
        }
    };


    Runnable runFar = new Runnable() {
        @Override
        public void run() {
            if (currentImage < 68) {
                imageView.setImageResource(imageId[++currentImage]);
                handlerNear.postDelayed(runFar, 30);
            }
        }
    };

    int mov_count = 2;
    int mov_threshold = 4;
    float alpha = 0.8f;
    int shake_interval = 500;
    float gravity[] = new float[3];
    int counter = 0;
    long firstMoveTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView =  findViewById(R.id.ivRose);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);

        for (int i = 0; i < sensors.size(); i++) {

            String name = sensors.get(i).getName();
            String company = sensors.get(i).getVendor();
            Toast.makeText(this, name + " : " + company, Toast.LENGTH_LONG).show();
        }

        proximitySensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        LightSensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        accelerometerSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sm.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        //sm.registerListener(this,LightSensor,SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);

        initialise();
    }

    void initialise() {

        for (int i = 1; i <= 69; i++) {

            int id = getResources().getIdentifier("rose" + i, "drawable", getPackageName());
            imageId[i-1] = id;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor == proximitySensor) {

            float value = sensorEvent.values[0];
            if (value < proximitySensor.getMaximumRange()) {
                if (handlerFar != null) {
                    handlerFar.removeCallbacks(runFar);
                }
                handlerNear = new Handler();
                handlerNear.post(runNear);

            } else {
                if (handlerNear != null) {
                    handlerNear.removeCallbacks(runNear);
                }
                handlerFar = new Handler();
                handlerFar.post(runFar);


            }
        } else if (sensorEvent.sensor == accelerometerSensor) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            float max = maxAcceleration(x, y, z);
            if (max > mov_threshold) {
                if (counter == 0) {
                    counter++;
                    firstMoveTime = System.currentTimeMillis();
                } else {
                    long now = System.currentTimeMillis();
                    long diff = now - firstMoveTime;

                    if (diff < shake_interval) {
                        counter++;
                    } else {
                        counter = 0;
                        firstMoveTime = System.currentTimeMillis();
                        return;
                    }
                    if (counter >= mov_count) {
                        RelativeLayout rl =  findViewById(R.id.rl);
                        Random random = new Random();

                        rl.setBackgroundColor(Color.rgb(
                                random.nextInt(255),
                                random.nextInt(255),
                                random.nextInt(255)));

                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    float maxAcceleration(float x, float y, float z) {

        gravity[0] = calGravity(x, 0);
        gravity[1] = calGravity(y, 1);
        gravity[2] = calGravity(z, 2);

        float fx = x - gravity[0];
        float fy = y - gravity[1];
        float fz = z - gravity[2];

        float max1 = Math.max(fx, fy);
        return Math.max(max1, fz);

    }

    float calGravity(float value, int index) {
        return alpha * gravity[index] + (1 - alpha) * value;
    }

}
