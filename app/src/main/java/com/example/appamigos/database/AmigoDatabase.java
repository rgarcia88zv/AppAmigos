package com.example.appamigos.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.appamigos.dao.AmigoDao;
import com.example.appamigos.dao.LlamadaDao;
import com.example.appamigos.pojo.Amigo;
import com.example.appamigos.pojo.Llamada;

@Database(entities = {Amigo.class, Llamada.class}, version = 1, exportSchema = false)
public abstract class AmigoDatabase extends RoomDatabase {

    public abstract AmigoDao getAmigoDao();
    public abstract LlamadaDao getLlamadaDao();
    private volatile static AmigoDatabase INSTANCE;

    public static synchronized AmigoDatabase getDb(final Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AmigoDatabase.class, "dbamigo.sqlite")
                    .build();
        }
        return INSTANCE;
    }

}
