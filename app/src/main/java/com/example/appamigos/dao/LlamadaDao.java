package com.example.appamigos.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appamigos.pojo.Amigo;
import com.example.appamigos.pojo.Llamada;

import java.util.List;

@Dao
public interface LlamadaDao {
    @Insert
    void insert(Llamada llamada);

    @Update
    void update(Llamada llamada);

    @Query("select * from llamada")
    LiveData<List<Llamada>> getLlamadas();

}
