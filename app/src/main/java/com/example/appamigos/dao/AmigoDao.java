package com.example.appamigos.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appamigos.pojo.Amigo;

import java.util.List;

@Dao
public interface AmigoDao {
    @Insert
  void insert(Amigo amigo);

    @Update
    void update(Amigo amigo);

    @Delete
    void delete(Amigo amigo);

    @Query("select * from amigo")
    LiveData<List<Amigo>> getAll();

    @Query("select id from amigo where telf = :telf")
    int getIdAmigo(String telf);




}
