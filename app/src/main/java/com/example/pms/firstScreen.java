package com.example.pms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.pms.Controller.AHNMS;
import com.example.pms.Controller.Credentials;
import com.example.pms.Model.OwnerDatabase;
import com.example.pms.Model.Plant;
import com.example.pms.Model.Pot;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class firstScreen extends AppCompatActivity {


    RecyclerView r1;
    MyOwnAdapter ad;
    FloatingActionButton addBtn;
    TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
      AHNMS  potsController = AHNMS.getInstance();
      potsController.init(firstScreen.this);        //getting pots list from database
        r1 = (RecyclerView) findViewById(R.id.myRec);
        heading = (TextView) findViewById(R.id.heading);
        Credentials user = Credentials.getInstance();
        String name = user.getName();

        heading.setText(user.getName() + "'s Plants");
        addBtn = (FloatingActionButton) findViewById(R.id.addbtn);
        ad = new MyOwnAdapter(firstScreen.this);
        r1.setAdapter(ad);
        r1.setLayoutManager(new LinearLayoutManager(this));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(firstScreen.this,AddPlantScreen.class);
                startActivity(i1);
            }
        });
//        Pot pot = new Pot();
//        pot.setPotName("potName1");
//        pot.setUid(1);
//        Plant p = new Plant();
//        p.setLightIntensity(10);
//        p.setWaterLevel(
//                10
//        );
//        p.setFertilizerLevel(10);
//        p.setPlantName("plant1");
//        pot.setPlant(p);
//        Pot pot2 = new Pot();
//        pot2.setPotName("potName2");
//        pot2.setUid(2);
//        Plant p2 = new Plant();
//        p2.setLightIntensity(10);
//        p2.setWaterLevel(
//                10
//        );
//        p2.setFertilizerLevel(10);
//        p2.setPlantName("plant2");
//        pot2.setPlant(p2);
//
//        OwnerDatabase db  = OwnerDatabase.getDbInstance(firstScreen.this);
//        db.potDao().insertPot(pot);
//        db.potDao().insertPot(pot2);
//        List<Pot> pots;
//        pots = db.potDao().getAllPots(2);


       // user = Credentials.getInstance();
//        t1.setText(pots.get(0).getPotName() + ", " + pots.get(0).getPlant().getPlantName() );
    }

    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
            ad.notifyDataSetChanged();
    }
}