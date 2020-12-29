package com.example.mascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity implements Serializable {

    ArrayList<Mascota> mascotasFavoritas;
    ArrayList<Mascota> mascotas;

    //Nombro el RecylcerView del layout activity_main
    private RecyclerView rvMascotasFavoritas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);


        Toolbar toolbar = findViewById(R.id.Actionbar2);
        setSupportActionBar(toolbar);

        //AGREGAR UN BOTON HACIA ARRIBA
        //NO ME FUNCIONÓ :(

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Doy vida a la lista de contactos tipo RecycleView, ya es un objeto manipulable con java
        rvMascotasFavoritas = (RecyclerView) findViewById(R.id.rvMascotasfavoritas);

        //De qué forma quiero mostrar mi RecycleView por medio de un objeto LinearLayoutManager o grid layout manager

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //Hago que el objeto rvContactos se comporte como un LinearLayout
        rvMascotasFavoritas.setLayoutManager(llm);
        inicializarListaMascotas();
        iniciarAdaptador();




    }

    private void iniciarAdaptador() {
        //Instanciación de contactoAdaptador, crear un objeto de MascotasFavoritasAdaptador, pasar la lista y pueda hacer lo configurado

        MascotasFavoritasAdaptador adaptador = new MascotasFavoritasAdaptador(mascotasFavoritas, this);
        rvMascotasFavoritas.setAdapter(adaptador);
    }

    //Bundle parametros = getIntent().getExtras();

    //Método para Inicializar u obtener la lista de masctotas en el ArrayList mascotasFavoritas

    public void inicializarListaMascotas () {

        //Genero Array List dentro del método


        //Aquí recibo el ArrayList que generé

        Bundle parametros = getIntent().getExtras();
        mascotasFavoritas = (ArrayList<Mascota>) parametros.getSerializable("Mascotas");


        //--------------------------------------------------------------------------------------------------------------
        //OMITIR LO QUE SIGUE ABAJOS, SOLO SON INTENTOS DE APRENDIZAJE :)

        /* PRUEBA DE OTRA MANERA DE OBTENER EL ArrayList

        //PRUEBA  mascotas = new ArrayList<Mascota>();

        for (int i = 0; i < mascotasFavoritas.size(); i++) {

            int fotoPerro  = mascotasFavoritas.get(i).getFotoPerro();
            int likesPerro = mascotasFavoritas.get(i).getLikesPerro();
            String nombreMascota = mascotasFavoritas.get(i).getNombreMascota();

            mascotas.add(new Mascota(nombreMascota, fotoPerro, likesPerro));

        }

         */


        // PRUEBA  mascotasFavoritas.add(new Mascota("Pepe", R.mipmap.dog1, 0) );

    }


}