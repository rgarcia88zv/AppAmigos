package com.example.appamigos.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appamigos.R;
import com.example.appamigos.adapter.AmigosAdapter;
import com.example.appamigos.pojo.Amigo;
import com.example.appamigos.pojo.Llamada;
import com.example.appamigos.viewmodel.ViewModelActivity;

import java.util.ArrayList;
import java.util.List;


public class AmigosListFragment extends Fragment {

    private ViewModelActivity viewModelActivity;
    private RecyclerView recyclerView;

    private AmigosAdapter adapter;
    private List<Amigo> amigos = new ArrayList<>();
    private List<Llamada> llamadas = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amigos_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        comprobarNumLlamadas();
        viewModelActivity = new ViewModelProvider(getActivity()).get(ViewModelActivity.class);

        recyclerView = view.findViewById(R.id.recycler_amigos);
        adapter = new AmigosAdapter(getContext(),amigos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModelActivity.getLiveAmigoList().observe(getActivity(), new Observer<List<Amigo>>() {
            @Override
            public void onChanged(List<Amigo> amigosLive) {
                amigos.clear();
                amigos.addAll(amigosLive);
                adapter.notifyDataSetChanged();
            }
        });


        init();
    }



    private void init() {
        getView().findViewById(R.id.fabAddAmigo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment
                        .findNavController(AmigosListFragment.this)
                        .navigate(R.id.importarFragment);
            }
        });

    }

    private void comprobarNumLlamadas(){

            viewModelActivity = new ViewModelProvider(getActivity()).get(ViewModelActivity.class);


            viewModelActivity.getLiveLlamadaList().observe(getActivity(), new Observer<List<Llamada>>() {
                @Override
                public void onChanged(List<Llamada> listLlamadas) {
                    llamadas.clear();
                    llamadas.addAll(listLlamadas);
                }
            });
                for (int i = 0; i < amigos.size(); i++) {
                    for (int j = 0; j < llamadas.size(); j++) {
                        if(amigos.get(i).getId()==llamadas.get(j).getIdAmigo()){
                            amigos.get(i).setNumLlamadas(amigos.get(i).getNumLlamadas()+1);


                        }
                    }

            }
        Log.v("numero",llamadas.toString());

        }


}