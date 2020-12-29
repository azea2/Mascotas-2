package com.example.mascotas;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    //VARIABLES GLOBALES
    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    //METODO USANDO LENGUAJE SQL Y LOS VALORES DE LA CLASE ConstatesBaseDatos--------------------------------------------------------------------
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS + "("+
                           ConstantesBaseDatos.TABLE_MASCOTAS_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                           ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE + " TEXT, " +
                           ConstantesBaseDatos.TABLE_MASCOTAS_FOTO   + " INTEGER" +
                ")";

        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + "(" +
                            ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                            ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + " INTEGER," +
                            ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES + " INTEGER," +
                            "FOREIGN KEY ("+ ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + ") " +
                            "REFERENCES "+ConstantesBaseDatos.TABLE_MASCOTAS + "("+ConstantesBaseDatos.TABLE_MASCOTAS_ID+")"+
                ")";

        sqLiteDatabase.execSQL(queryCrearTablaMascota);
        sqLiteDatabase.execSQL(queryCrearTablaLikesMascota);
    }

    //METODO QUE SE EJECUTA CUANDO NECESITAMOS REESTRUCTURAR LA BASE DE DATOS-----------------------------------------------------------------
    //AFECTA A LA BASE DE DATOS.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTAS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA);
        onCreate(sqLiteDatabase);
    }

    //METODO PARA OBTENER TODAS LAS MASCOTAS-----------------------------------------------------------------------------------------------------

    public ArrayList<Mascota> obtenerTodosLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM "+ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombreMascota(registros.getString(1));
            mascotaActual.setFotoPerro(registros.getInt(2));

            String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES+") as likes " +
                    " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                    " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + "=" + mascotaActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes,null);

            if(registrosLikes.moveToNext()){
                mascotaActual.setLikesPerro(registrosLikes.getInt(0));
            }else{
                mascotaActual.setLikesPerro(0);
            }

            mascotas.add(mascotaActual);
        }

        db.close();

        return mascotas;
    }

    //METODO PARA INSERTAR MASCOTAS--------------------------------------------------------------------------------------------------------
    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS, null, contentValues);
        db.close();
    }

    //METODO PARA MASCOTAS LIKES--------------------------------------------------------------------------------------------------------
    public void insertarLikeContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTA, null, contentValues);
        db.close();
    }

    //METODO QUE DEVUELVE LA CANTIDAD DE LIKES
    public int obtenerLikesMascotas(Mascota mascota){
        int likes = 0;

        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES+")" +
                " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + "="+mascota.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();
        return likes;
    }


}
