package com.example.pms.Controller;


import android.content.Context;

import com.example.pms.Model.OwnerDatabase;
import com.example.pms.Model.Plant;
import com.example.pms.Model.Pot;

import java.util.List;

public class AHNMS {
    private List<Pot> pots;
    private static AHNMS INSTANCE;

    public static AHNMS getInstance() {

        if(INSTANCE == null) {
            INSTANCE = new AHNMS();
        }
        return INSTANCE;
    }
    public void init(Context context){
        Credentials user = Credentials.getInstance();
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        pots = db.potDao().getAllPots(user.getUid());
        for(int i=0;i<pots.size();++i){
            makeThread(i);
        }
    }

    public void updateLevel(int index){
        pots.get(index).updateLevel();
    }

    public String getPotName(int idx){
        return pots.get(idx).getPotName();
    }

    public int getNoOfPots(){
        return pots.size();
    }

    public void addPot(String name, Plant plant, Context context){
        Pot pot = new Pot();
        pot.setPotName(name);
        pot.setPlant(plant);
        Credentials user = Credentials.getInstance();
        pot.setUid(user.getUid());
        pots.add(pot);
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        db.potDao().insertPot(pot);

    }

    public void removePot(int id,Context context){
        int idx = -1;
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        for(int i=0;i<pots.size();++i){
            if(pots.get(i).getPotId() == id){
                db.potDao().delete(pots.get(i));
                idx = i;
                break;
            }
        }
        pots.remove(idx);
    }
    public void modifyPot(int id,Plant plant,Context context){
        //int idx = -1;
       // String potName;
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        for(int i=0;i<pots.size();++i){
            if(pots.get(i).getPotId() == id){

                db.potDao().delete(pots.get(i));
                //potName = pots.get(i).getPotName();
                pots.get(i).modifyPlant(plant);
                db.potDao().insertPot(pots.get(i));
               // idx = i;
                break;
            }
        }
       // pots.remove(idx);
    }
    public void removePlant(int id){
        int idx = -1;
        for(int i=0;i<pots.size();++i){
            if(pots.get(i).getPotId() == id){
               // db.potDao().delete(pots.get(i));
                pots.get(i).removePlant();
                break;
            }
        }
    }
    public void addPlant(int id,Plant plant){
        int idx = -1;
        for(int i=0;i<pots.size();++i){
            if(pots.get(i).getPotId() == id){
                // db.potDao().delete(pots.get(i));
                pots.get(i).addPlant(plant);
                break;
            }
        }
    }
    public void manageFertilizationLevel(int id,double fertilizationLevel,Context context){
        int idx = -1;
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        for(int i=0;i<pots.size();++i){
            if(pots.get(i).getPotId() == id){
                // db.potDao().delete(pots.get(i));
                db.potDao().delete(pots.get(i));

                pots.get(i).updateFertilizerLevel(fertilizationLevel);
                db.potDao().insertPot(pots.get(i));
                break;
            }
        }
    }
    public void manageWaterLevel(int id,double waterLevel,Context context){
        int idx = -1;
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        for(int i=0;i<pots.size();++i){
            if(pots.get(i).getPotId() == id){
                // db.potDao().delete(pots.get(i));
                db.potDao().delete(pots.get(i));

                pots.get(i).updateFertilizerLevel(waterLevel);
                db.potDao().insertPot(pots.get(i));
                break;
            }
        }
    }
    public void manageLightIntensity(int id,double lightIntensity,Context context){
        int idx = -1;
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        for(int i=0;i<pots.size();++i){
            if(pots.get(i).getPotId() == id){
                // db.potDao().delete(pots.get(i));
                db.potDao().delete(pots.get(i));

                pots.get(i).updateFertilizerLevel(lightIntensity);
                db.potDao().insertPot(pots.get(i));
                break;
            }
        }
    }

    public Plant retrieveInfo(int index){
//        for(int i=0;i<pots.size();++i){
//            if(pots.get(i).getPotId() == id){
//                // db.potDao().delete(pots.get(i));
//               return pots.get(i).getPlant();
//
//            }
//        }
        return pots.get(index).getPlant();
    }

    public void makeThread(int idx){
        new Thread() {
            public void run() {

                while (true) {

                   pots.get(idx).updateLevel();



                    try {
                        // i[0]++;
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//


                }
            }
        }.start();
    }


}
