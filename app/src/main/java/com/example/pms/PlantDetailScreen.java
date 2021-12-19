package com.example.pms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pms.Controller.AHNMS;
import com.example.pms.Model.Plant;

public class PlantDetailScreen extends AppCompatActivity {

    TextView plantName;
    TextView waterLevel;
    TextView fertilizerLevel;
    TextView sunlightIntensity;
    AHNMS potsController;
    ImageView plantImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail_screen);
        waterLevel = (TextView) findViewById(R.id.waterTxt);
        plantName = (TextView) findViewById(R.id.plantName);
        fertilizerLevel = (TextView) findViewById(R.id.fertilizerText);
        sunlightIntensity = (TextView) findViewById(R.id.sunlightText);
        plantImg = (ImageView) findViewById(R.id.plantImg);
        potsController = AHNMS.getInstance();
        Bundle b1 = getIntent().getExtras();
        int index = b1.getInt("idx");
        Plant plant = potsController.retrieveInfo(index);
        plantName.setText(plant.getPlantName());
        if(plant.getPlanttype().compareTo("daffodils") == 0){
            plantImg.setImageResource(R.drawable.daffoldils);
        }
        else if(plant.getPlanttype().compareTo("sunflower") == 0){
            plantImg.setImageResource(R.drawable.sunflower);
        }
        else if(plant.getPlanttype().compareTo("rose") == 0){
            plantImg.setImageResource(R.drawable.rose);
        }
        else if(plant.getPlanttype().compareTo("cactus") == 0){
            plantImg.setImageResource(R.drawable.cactus);
        }
        else if(plant.getPlanttype().compareTo("lilly") == 0){
            plantImg.setImageResource(R.drawable.lily);
        }

        Thread t1 =   new Thread() {
            @Override
            public void run() {
                while (true) {


                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            waterLevel.setText("Water Level: " + plant.getCurrentWaterLevel());
                            fertilizerLevel.setText("Fertilization Level: " + plant.getFertilizerLevel());
                            sunlightIntensity.setText("Light Intensity: " + ((plant.getCurrentLightIntensity() < 40) ? "on" : "off"));
                        }
                    });
                   // potsController.updateLevel(index);
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        };
        t1.start();
    }
}