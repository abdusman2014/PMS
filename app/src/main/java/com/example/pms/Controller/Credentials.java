package com.example.pms.Controller;

import android.content.Context;



import com.example.pms.Model.Owner;
import com.example.pms.Model.OwnerDatabase;

public class Credentials {

    Owner owner;

    private static Credentials INSTANCE;

    public static Credentials getInstance() {

        if(INSTANCE == null) {
            INSTANCE = new Credentials();

        }
        return INSTANCE;
    }

    public boolean signUp(String userName,String password, Context context){
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);



        if(!validate(password)){
            return false;
        }
        owner = new Owner();
       owner.setUserName(userName);
       owner.setPassword(password);
        db.userDao().insertUser(owner);

        return true;
    }

    public boolean logIn(String userName,String password, Context context){
        OwnerDatabase db  = OwnerDatabase.getDbInstance(context);
        Owner userList =db.userDao().getOwner(userName,password);
        if(userList != null){
            return true;
        }
        else{
            return false;
        }




    }

    private boolean validate(String password){
        if(password.length()<8){
            return false;
        }
        return true;
    }
}