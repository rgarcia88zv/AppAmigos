package com.example.appamigos.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appamigos.R;
import com.example.appamigos.dao.LlamadaDao;
import com.example.appamigos.pojo.Amigo;
import com.example.appamigos.pojo.Llamada;
import com.example.appamigos.viewmodel.ViewModelActivity;

import java.util.ArrayList;
import java.util.List;

public class EditarFragment extends Fragment {
   private Amigo amigo;
    private ViewModelActivity viewModelActivity;
    List<Amigo> listaAmigos;
    List<Llamada> listaLlamadas;
    int llamadasC=0;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelActivity = new ViewModelProvider(getActivity()).get(ViewModelActivity.class);
        listaAmigos = new ArrayList<>();
        listaLlamadas = new ArrayList<>();
        amigo = (Amigo) getArguments().getSerializable("amigo");

        EditText etNombre = view.findViewById(R.id.etNombre);
        EditText etTelf = view.findViewById(R.id.etTelf);
        EditText etFechaNac = view.findViewById(R.id.etFechaNac);
        TextView tvNumLlamadas = view.findViewById(R.id.tvLlamadasR);

        etNombre.setText(amigo.getNombre());
        etTelf.setText(amigo.getTelf());
        etFechaNac.setText(amigo.getFecNac());

        viewModelActivity.getLiveLlamadaList().observe(getActivity(), new Observer<List<Llamada>>() {
            @Override
            public void onChanged(List<Llamada> llamadas) {

                listaLlamadas.clear();
                listaLlamadas.addAll(llamadas);

                for (int i = 0; i <listaLlamadas.size() ; i++) {
                    if(listaLlamadas.get(i).getIdAmigo()==amigo.getId()){
                        llamadasC++;

                    }

                }
            }
        });


        tvNumLlamadas.setText("Numero de llamadas: " + llamadasC);


        viewModelActivity.getLiveAmigoList().observe(getActivity(), new Observer<List<Amigo>>() {
            @Override
            public void onChanged(List<Amigo> amigos) {
                listaAmigos.clear();
                listaAmigos.addAll(amigos);
            }
        });






        Button btBorrar = view.findViewById(R.id.btBorrar);
        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <listaAmigos.size() ; i++) {
                    if(listaAmigos.get(i).getId()==amigo.getId()){
                        viewModelActivity.delete(listaAmigos.get(i));
                        Toast.makeText(getContext(),"Amigo eliminado correctamente",Toast.LENGTH_SHORT).show();
                        NavHostFragment
                                .findNavController(EditarFragment.this)
                                .navigate(R.id.amigosListFragment);
                        break;
                    }

                }

            }
        });

        Button btActualizar = view.findViewById(R.id.btActualizar);
        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listaAmigos.size(); i++) {
                    if(listaAmigos.get(i).getId()==amigo.getId()){
                        amigo.setNombre(etNombre.getText().toString());
                        amigo.setFecNac(etFechaNac.getText().toString());
                            viewModelActivity.update(amigo);
                            Log.v("lista",listaAmigos.get(i).toString());
                             Toast.makeText(getContext(),"Amigo actualizado correctamente",Toast.LENGTH_SHORT).show();
                        NavHostFragment
                                .findNavController(EditarFragment.this)
                                .navigate(R.id.amigosListFragment);
                            break;
                    }
                }
            }
        });

    }
}