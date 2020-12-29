package com.example.mascotas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FragmentPerfil extends Fragment {

    ArrayList<Mascota> mascotasLista;

    //Array List con objetos tipo Mascota (Método constructor de la clase Mascota)
    ArrayList<Mascota> mascotas;


    //Nombro el RecylcerView del layout activity_main
    private RecyclerView rvMascotas;

    public FragmentPerfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        //Doy vida a la lista de contactos tipo RecycleView, ya es un objeto manipulable con java
        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        //De qué forma quiero mostrar mi RecycleView por medio de un objeto LinearLayoutManager o grid layout manager

        GridLayoutManager llm = new GridLayoutManager(getActivity(),2,LinearLayoutManager.VERTICAL,false);
        llm.setOrientation(GridLayoutManager.VERTICAL);

        //Hago que el objeto rvContactos se comporte como un LinearLayout
        rvMascotas.setLayoutManager(llm);
        inicializarListaMascotas();
        iniciarAdaptador();


        // Inflate the layout for this fragment

        return v;


    }


    private void iniciarAdaptador() {
        //Instanciación de contactoAdaptador, crear un objeto de contactoAdaptador, pasar la lista y pueda hacer lo configurado
        MiMascotaPerfilAdaptador adaptador = new MiMascotaPerfilAdaptador(mascotas, getActivity());
        rvMascotas.setAdapter(adaptador);
        mascotasLista = (ArrayList<Mascota>) adaptador.mascotasFavoritas;

    }


    //Método para Inicializar u obtener la lista de masctotas en el ArrayLista mascotas
    public void inicializarListaMascotas () {

        //Genero Array List dentro del método
        mascotas = new ArrayList<Mascota>();

        //Agrego los valores correspondientes para cada objeto dentro del ArrayList
        mascotas.add(new Mascota("", R.mipmap.dog2, 20));
        mascotas.add(new Mascota("", R.mipmap.dog2, 12));
        mascotas.add(new Mascota("", R.mipmap.dog2, 5));
        mascotas.add(new Mascota("", R.mipmap.dog2, 12));
        mascotas.add(new Mascota("", R.mipmap.dog2, 13));
        mascotas.add(new Mascota("", R.mipmap.dog2, 20));
        mascotas.add(new Mascota("", R.mipmap.dog2, 12));
        mascotas.add(new Mascota("", R.mipmap.dog2, 5));
        mascotas.add(new Mascota("", R.mipmap.dog2, 12));
        mascotas.add(new Mascota("", R.mipmap.dog2, 13));
    }


}