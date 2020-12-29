package com.example.mascotas;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

public class ConstructorMascotas {
    private static final Integer LIKE = 1;
    //COMENTARIOS INICIALES
     //----------------------------------------------------------------------------------------------------------------------------
    //Tambien llamada clase interactor (Intermediario con la clase que directamente consulta la base de datos)
    //Construye los diferentes vistas que necesite en el momento.
    //Clase clave para switchear la fuente de datos o web-service
    //-----------------------------------------------------------------------------------------------------------------------------

    //INICIO CODIGO

    private Context context;



    //CONSTRUCTOR ConstructorMascotas-------------------------------------------------------------------------------------------------------------
    public ConstructorMascotas(Context context) {
        this.context = context;
    }



    //METODO obtenerDatos ArrayList<Mascota>--------------------------------------------------------------------------------------------------------------
    //No importa de donde vengan los datos, pero siempre deben llegar en un ArrayList
    public ArrayList<Mascota> obtenerDatos(){

        BaseDatos db = new BaseDatos(context);
        insertarCincoMascotas(db);
        return  db.obtenerTodosLasMascotas();

         //PROCESO A MANO
        /* ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota("Pepe", R.mipmap.dog1, 0));
        mascotas.add(new Mascota("Lala", R.mipmap.dog2, 0));
        mascotas.add(new Mascota("Lalo", R.mipmap.dog3, 0));
        mascotas.add(new Mascota("Toby", R.mipmap.dog4, 0));
        mascotas.add(new Mascota("Luna", R.mipmap.dog5, 0));
        return mascotas;

         */
    }

    //METODO QUE INSERTA MASCOTAS-------------------------------------------------------------------------------------------------------------
    public void insertarCincoMascotas(BaseDatos db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Pepe");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.mipmap.dog1);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Lala");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.mipmap.dog2);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Lalo");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.mipmap.dog3);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Toby");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.mipmap.dog4);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Luna");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.mipmap.dog5);

        db.insertarMascota(contentValues);

    }

    //DAR LIKE MASCOTA-------------------------------------------------------------------------------------------------------------------------
    public void darLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES, LIKE);
        db.insertarLikeContacto(contentValues);
    }

    //OBTENER LIKES MASCOTA----------------------------------------------------------------------------------------------------------------------
    public int obtenerLikesMascota(Mascota mascota){
           BaseDatos db = new BaseDatos(context);
           return db.obtenerLikesMascotas(mascota);
    }
}
