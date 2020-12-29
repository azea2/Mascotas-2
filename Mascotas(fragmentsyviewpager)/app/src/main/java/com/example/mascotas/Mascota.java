package com.example.mascotas;

import java.io.Serializable;

public class Mascota implements Serializable {



    private int id;
    String nombreMascota;
    int fotoPerro;
    int likesPerro;

    //METODO CONSTRUCTOR DE CLASE Mascota

    public Mascota(String nombreMascota, int fotoPerro, int likesPerro) {
        this.nombreMascota = nombreMascota;
        this.fotoPerro = fotoPerro;
        this.likesPerro = likesPerro;
    }

    public Mascota() {

    }

    //GETTER AND SETTERS DE nombreMascota y fotoPerro

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public int getFotoPerro() {
        return fotoPerro;
    }

    public void setFotoPerro(int fotoPerro) {
        this.fotoPerro = fotoPerro;
    }

    public int getLikesPerro() {
        return likesPerro;
    }

    public void setLikesPerro(int likesPerro) {
        this.likesPerro = likesPerro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
