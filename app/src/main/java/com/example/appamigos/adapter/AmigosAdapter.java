package com.example.appamigos.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appamigos.R;
import com.example.appamigos.fragments.AmigosListFragment;
import com.example.appamigos.pojo.Amigo;

import java.util.ArrayList;
import java.util.List;

public class AmigosAdapter extends RecyclerView.Adapter<AmigosAdapter.ViewHolder>  {
    private Context contexto;
    private List<Amigo> misAmigos;


    public AmigosAdapter(Context contexto, List<Amigo> misAmigos) {
        this.contexto = contexto;
        this.misAmigos = misAmigos;
    }

    @NonNull
    @Override
    public AmigosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amigos,parent,false);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AmigosAdapter.ViewHolder holder, int position) {
    holder.tvNombre.setText(misAmigos.get(position).getNombre());
    holder.tvTelf.setText(misAmigos.get(position).getTelf());
    holder.tvFecNac.setText(misAmigos.get(position).getFecNac());
    holder.tvNumLlamadas.setText(String.valueOf(misAmigos.get(position).getNumLlamadas()));
    holder.parent_layout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("amigo",misAmigos.get(position));
            Navigation.createNavigateOnClickListener(R.id.editarFragment,bundle).onClick(holder.itemView);

        }
    });

    }

    @Override
    public int getItemCount() {
        return misAmigos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvTelf;
        TextView tvFecNac;
        TextView tvNumLlamadas;
        ConstraintLayout parent_layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreA);
            tvTelf = itemView.findViewById(R.id.tvTelfA);
            tvFecNac = itemView.findViewById(R.id.tvFechaA);
            tvNumLlamadas = itemView.findViewById(R.id.tvLlamadasA);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
