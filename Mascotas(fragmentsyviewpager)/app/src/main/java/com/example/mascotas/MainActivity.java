package com.example.mascotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {

    private ViewPager viewPager;

    //VARIABLES PARA USO DE FRAGMENTS
    private Toolbar actionBar;
    private TabLayout tabLayout;
    ArrayList<Mascota> mascotasLista;

    //Array List con objetos tipo Mascota (Método constructor de la clase Mascota)
    ArrayList<Mascota> mascotas;
    private ImageButton star;
    private RecyclerView rvMascotas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        actionBar = findViewById(R.id.Actionbar);
        setUpViewPager();


        //Soporte a la toolbar o actionbar en este caso
        if (actionBar != null) {
            setSupportActionBar(actionBar);
        }
    }

    //PROCESO DE FRAGMENTS: CREO METODO:
    //PONE EN ORBITA LOS FRAGMENTS

    private ArrayList<Fragment> agregarFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new FragmentPerfil());

        return fragments;
    }


    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragment()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_home_24);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_ballot_24);
    }


    //-----------------------------------------------------------------------------------------------------------------------------
    //CREO 2 METODOS onCreateOptionsMenu y onOptionsItemSelected PARA MENU DE OPCIONES.

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mContacto:
                Intent intent = new Intent(this, Contacto.class);
                startActivity(intent);
                break;
            case R.id.mAcercaDe:
                Intent intent1 = new Intent(this, AcercaDe.class);
                startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }
}

    //------------------------------------------------------------------------------------------------------------------------------

