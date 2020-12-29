package com.example.mascotas;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {

    //CREO VARIABLE PARA QUE EL PAGE ADAPTER PUEDA VER LOS FRAGMENTS.
    private ArrayList<Fragment> fragments;



    //----------------------------------------------------------------------------------------------
    //IMPLEMENTO EL CONSTRUCTOR QUE ME SOLICITA
    //AGREGO UN ARREGLO FRAGMENT AL METODO
    public PageAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }



    //----------------------------------------------------------------------------------------------
    //IMPLEMENTO METODOS QUE ME SOLICITA
    @NonNull
    @Override

    public Fragment getItem(int position) {
        //CAMBIO return null por return fragments.get(position)
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        //DUVUELVO EL TAMAÑO DE LA LISTA DE FRAGMENT
        return fragments.size();
    }
}
