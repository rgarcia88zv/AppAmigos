package com.example.appamigos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.appamigos.pojo.Amigo;
import com.example.appamigos.repository.Repository;

import java.util.List;

public class LlamadasReceiver extends BroadcastReceiver {
    Repository repository;
    List<Amigo> listaAmigos;
    String numero;
    @Override
    public void onReceive(Context context, Intent intent) {
        repository = new Repository(context);

        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            numero = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Log.v("numero",numero);
                repository.insertLlamada(numero);
        }




    }




}