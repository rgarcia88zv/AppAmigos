package com.example.appamigos.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appamigos.R;
import com.example.appamigos.adapter.ContactosAdapter;
import com.example.appamigos.pojo.Amigo;
import com.example.appamigos.viewmodel.ViewModelActivity;

import java.util.ArrayList;

public class ImportarFragment extends Fragment {
    String[] projection = new String[]{ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

    String selectionClause = ContactsContract.Data.MIMETYPE + "='" +
            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
            + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

    String sortOrder = ContactsContract.Data.DISPLAY_NAME + " ASC";

    ArrayList<Amigo> listaContactos;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_importar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listaContactos = new ArrayList<>();
        obtenerContactos(getContext());

        RecyclerView miRecycler = view.findViewById(R.id.recyclerContacts);
        RecyclerView.Adapter adapter = new ContactosAdapter(getContext(),listaContactos,getActivity());
        miRecycler.setAdapter(adapter);
        miRecycler.setLayoutManager(new LinearLayoutManager(getContext()));



    }

    private void obtenerContactos(Context context) {
        Cursor c = context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                projection,
                selectionClause,
                null,
                sortOrder);

        while (c.moveToNext()) {
            int id = Integer.parseInt(c.getString(0));
            String nombre = c.getString(1);
            String numero = c.getString(2);
            Amigo a = new Amigo(nombre,null,numero,0);


            listaContactos.add(a);


        }
        c.close();

    }
}