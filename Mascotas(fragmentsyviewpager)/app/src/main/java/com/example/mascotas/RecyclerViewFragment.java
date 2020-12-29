package com.example.mascotas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView{


    ArrayList<Mascota> mascotasLista;

    //Array List con objetos tipo Mascota (Método constructor de la clase Mascota)
    ArrayList<Mascota> mascotas;


    //Nombro el RecylcerView del layout activity_main
    private RecyclerView rvMascotas;
    private ImageButton star;
    private Toolbar Actionbar;
    private IRecyclerViewFragmentPresenter presenter;



    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        star = v.findViewById(R.id.star);


        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MascotasFavoritas.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("Mascotas", mascotasLista);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



        //Doy vida a la lista de contactos tipo RecycleView, ya es un objeto manipulable con java
        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        presenter = new RecyclerViewFragmentPresenter(this,getContext());
        return v;


    }//FIN DEL METODO ONCREATEVIEW




    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvMascotas.setAdapter(adaptador);
        mascotasLista = (ArrayList<Mascota>) adaptador.mascotasFavoritas;

    }
}