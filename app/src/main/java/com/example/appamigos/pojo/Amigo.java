package com.example.appamigos.pojo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "amigo", indices = {@Index(value = {"telf"}, unique = true)})
public class Amigo implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @Nullable
    @ColumnInfo(name = "fecNac")
    private String fecNac;

    @NonNull
    @ColumnInfo(name = "telf")
    private String telf;

    @Nullable
    @ColumnInfo(name = "numLlamadas")
    private int numLlamadas;


    public Amigo(@NonNull String nombre,@Nullable String fecNac, String telf, @Nullable int numLlamadas) {
        this.nombre = nombre;
        this.fecNac = fecNac;
        this.telf = telf;
        this.numLlamadas = numLlamadas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @Nullable
    public String getFecNac() {
        return fecNac;
    }

    public void setFecNac(@Nullable String fecNac) {
        this.fecNac = fecNac;
    }

    @NonNull
    public String getTelf() {
        return telf;
    }

    public void setTelf(@NonNull String telf) {
        this.telf = telf;
    }

    public int getNumLlamadas() {
        return numLlamadas;
    }

    public void setNumLlamadas(int numLlamadas) {
        this.numLlamadas = numLlamadas;
    }

    @Override
    public String toString() {
        return "Amigo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecNac='" + fecNac + '\'' +
                ", telf=" + telf +
                ", numLlamadas=" + numLlamadas +
                '}';
    }
}
