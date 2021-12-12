package com.example.pms.Model;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OwnerDao {
    @Query("SELECT * FROM Owner")
    List<Owner> getAllUsers();

    @Query("SELECT * FROM Owner WHERE user_name = (:userName) AND password = (:password)")
    Owner getOwner(String userName, String password);

    @Insert
    void insertUser(Owner... users);

    @Delete
    void delete(Owner user);
}
