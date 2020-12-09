package com.example.appamigos.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appamigos.repository.Repository;
import com.example.appamigos.pojo.Amigo;
import com.example.appamigos.pojo.Llamada;

import java.util.List;

public class ViewModelActivity extends AndroidViewModel {

    private Repository repository;

    public ViewModelActivity(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public Amigo getAmigo() {
        return repository.getCurrentAmigo();
    }

    public LiveData<List<Amigo>> getLiveAmigoList() {
        return repository.getLiveAmigoList();
    }

    public LiveData<List<Llamada>> getLiveLlamadaList() {
        return repository.getLiveLlamadaList();
    }

    public MutableLiveData<Long> getLiveAmigoInsertId() {
        return repository.getLiveAmigoInsertId();
    }

    public void insert(Amigo amigo) {
        repository.insert(amigo);
    }

    public void setAmigo(Amigo amigo) {
        repository.setCurrentAmigo(amigo);
    }

    public void update(Amigo amigo) {
        repository.update(amigo);
    }

    public void delete(Amigo amigo) {
        repository.delete(amigo);
    }

}
