package com.example.pms.Model;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PotDao {

    @Query("SELECT * FROM Pot WHERE uid = (:uid)")
    List<Pot> getAllPots(int uid);

    @Insert
    void insertPot(Pot... pot);

    @Delete
    void delete(Pot pot);
}
