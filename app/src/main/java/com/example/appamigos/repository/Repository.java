package com.example.appamigos.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appamigos.dao.AmigoDao;
import com.example.appamigos.dao.LlamadaDao;
import com.example.appamigos.database.AmigoDatabase;
import com.example.appamigos.pojo.Amigo;
import com.example.appamigos.pojo.Llamada;
import com.example.appamigos.util.UtilThread;
import com.example.appamigos.viewmodel.ViewModelActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Repository {

    private Amigo currentAmigo;
    private AmigoDao amigoDao;

    private Llamada currentLlamada;
    private LlamadaDao llamadaDao;

    private LiveData<List<Amigo>> liveAmigoList;
    private MutableLiveData<Long> liveAmigoInsertId = new MutableLiveData<>();

    private LiveData<List<Llamada>> liveLlamadaList;
    private MutableLiveData<Long> liveLlamadaInsertId = new MutableLiveData<>();

    private List<Amigo> amigos = new ArrayList<>();

    public Repository(Context context){
        AmigoDatabase db = AmigoDatabase.getDb(context);
        amigoDao = db.getAmigoDao();
        llamadaDao=db.getLlamadaDao();
        liveAmigoList = amigoDao.getAll();
        liveLlamadaList = llamadaDao.getLlamadas();

    }
    public Amigo getCurrentAmigo() {
        return currentAmigo;
    }
    public Llamada getCurrentLlamada() {
        return currentLlamada;
    }

    public MutableLiveData<Long> getLiveAmigoInsertId() {
        return liveAmigoInsertId;
    }

    public MutableLiveData<Long> getLiveLlamadaInsertId() {
        return liveLlamadaInsertId;
    }

    public LiveData<List<Amigo>> getLiveAmigoList() {
        return liveAmigoList;
    }
    public LiveData<List<Llamada>> getLiveLlamadaList() {
        return liveLlamadaList;
    }

    public void insert(Amigo amigo) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                   amigoDao.insert(amigo);
                    Log.v("xyz", "Insertado");
                } catch (Exception e) {
                    e.getMessage();
                    Log.v("xyz", e.toString());

                }
            }
        });

    }

    public void setCurrentAmigo(Amigo currentAmigo) {
        this.currentAmigo = currentAmigo;
    }

    public void setCurrentLlamada(Llamada currentLlamada) {
        this.currentLlamada = currentLlamada;
    }

    public void update(Amigo amigo) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    amigoDao.update(amigo);
                } catch (Exception e) {
                    Log.v("xyz", e.toString());
                }
            }
        });
    }

    public void delete(Amigo amigo){
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    amigoDao.delete(amigo);
                } catch (Exception e) {
                    Log.v("xyz", e.toString());
                }

            }
        });

    }


    public void insertLlamada(String telf) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                   int idAmigo = amigoDao.getIdAmigo(telf);
                    String format = "yyyy; MM; dd; HH; mm; ss";
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat(format);
                    String now = df.format(calendar.getTime());
                    Llamada llamada = new Llamada(idAmigo,now);
                    Log.v("llamada",llamada.toString());
                    llamadaDao.insert(llamada);

                    Log.v("xyz", "Insertado");
                } catch (Exception e) {
                    e.getMessage();
                    Log.v("xyz", e.toString());

                }
            }
        });

    }

    public void updateLlamada(Llamada llamada) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    llamadaDao.update(llamada);
                } catch (Exception e) {
                    Log.v("xyz", e.toString());
                }
            }
        });
    }

}
