package com.example.appamigos.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appamigos.R;
import com.example.appamigos.pojo.Amigo;
import com.example.appamigos.viewmodel.ViewModelActivity;

import java.util.ArrayList;

public class ContactosAdapter  extends RecyclerView.Adapter<ContactosAdapter.ViewHolder> {
    private ViewModelActivity viewModelActivity;
    private Context contexto;
    private ArrayList<Amigo> misContactos;
    private Activity activity;

    public ContactosAdapter(Context contexto, ArrayList<Amigo> misContactos,Activity activity) {
        this.contexto = contexto;
        this.misContactos = misContactos;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ContactosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewModelActivity = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModelActivity.class);
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contactos,parent,false);
        ContactosAdapter.ViewHolder holder = new ContactosAdapter.ViewHolder(vista);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ContactosAdapter.ViewHolder holder, int position) {

        holder.tvName.setText(misContactos.get(position).getNombre());
        holder.tvTel.setText(misContactos.get(position).getTelf());
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amigo a = new Amigo(misContactos.get(position).getNombre(),null,misContactos.get(position).getTelf(),0);
                Log.v("xyz",misContactos.get(position).toString());
                viewModelActivity.insert(a);
                Toast.makeText(contexto,"Contacto agregado como amigo",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return misContactos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvTel;
        ConstraintLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNombreContacto);
            tvTel = itemView.findViewById(R.id.tvNumContacto);
            parent_layout = itemView.findViewById(R.id.contactoLayout);
        }
    }
}
