package com.example.appamigos.pojo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "llamada", foreignKeys = @ForeignKey(entity = Amigo.class, parentColumns = "id", childColumns = "idAmigo", onDelete = ForeignKey.RESTRICT))
public class Llamada {

    @PrimaryKey(autoGenerate = true)
    private long idLlamada;

    @NonNull
    @ColumnInfo(name = "idAmigo")
    private int idAmigo;

    @Nullable
    @ColumnInfo(name = "fechaLlamada")
    private String fechaLlamada;


    public Llamada(int idAmigo, @Nullable String fechaLlamada) {
        this.idAmigo = idAmigo;
        this.fechaLlamada = fechaLlamada;
    }

    public long getIdLlamada() {
        return idLlamada;
    }

    public void setIdLlamada(long idLlamada) {
        this.idLlamada = idLlamada;
    }

    public int getIdAmigo() {
        return idAmigo;
    }

    public void setIdAmigo(int idAmigo) {
        this.idAmigo = idAmigo;
    }

    @Nullable
    public String getFechaLlamada() {
        return fechaLlamada;
    }

    public void setFechaLlamada(@Nullable String fechaLlamada) {
        this.fechaLlamada = fechaLlamada;
    }

    @Override
    public String toString() {
        return "Llamada{" +
                "idLlamada=" + idLlamada +
                ", idAmigo='" + idAmigo + '\'' +
                ", fechaLlamada='" + fechaLlamada + '\'' +
                '}';
    }
}
