package com.gestionarrecoleccion.gestionarrecoleccion.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gestionarrecoleccion.gestionarrecoleccion.entidades.NovedadOrdenCargueEntidad;

/**
 * Created by Alejandra on 16/12/2016.
 */

public class TbItemnovedadordencargue extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bd_redetrans.db";

    public TbItemnovedadordencargue(GrabarNovedad grabarNovedad, String bd_redetrans, Context context, int i) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bd)
    {
        //Sentencia SQL para crear la tabla de tb_itemnovedadordencargue
        String sqlCrear = "CREATE TABLE " + NovedadOrdenCargueEsquema.NovedadOrdenCargue.tablaNombre + " ("
                + NovedadOrdenCargueEsquema.NovedadOrdenCargue.novedadCodigo + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NovedadOrdenCargueEsquema.NovedadOrdenCargue.ordenCargueCodigo + " TEXT NOT NULL,"
                + NovedadOrdenCargueEsquema.NovedadOrdenCargue.novedadObservacion + " TEXT NOT NULL,"
                + NovedadOrdenCargueEsquema.NovedadOrdenCargue.tipoNovedadCodigo + " TEXT NOT NULL,"
                + NovedadOrdenCargueEsquema.NovedadOrdenCargue.novedadFechaCreacion + " TEXT NOT NULL,"
                + NovedadOrdenCargueEsquema.NovedadOrdenCargue.novedadHoraCreacion + " TEXT NOT NULL,"
                + NovedadOrdenCargueEsquema.NovedadOrdenCargue.novedadUsuarioCodigo + " TEXT NOT NULL,"
                + NovedadOrdenCargueEsquema.NovedadOrdenCargue.novedadSincronizado + " TEXT NOT NULL"
                + " )";
        //Se ejecuta la sentencia SQL de creación de la tabla
        bd.execSQL(sqlCrear);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAnterior, int versionNueva)
    {
        /*if(versionAnterior == 1 && versionNueva == 2){
            bd.execSQL(sqlActualizar);
        }*/
        //Se elimina la versión anterior de la tabla
        //bd.execSQL("DROP TABLE IF EXISTS tb_itemnovedadordencargue");

        //Se crea la nueva versión de la tabla
        //bd.execSQL(sqlCrear);
    }

    public long crearNovedad(NovedadOrdenCargueEntidad novedadOrdenCargueEntidad)
    {
        SQLiteDatabase bd = getWritableDatabase();

        return bd.insert(
                NovedadOrdenCargueEsquema.NovedadOrdenCargue.tablaNombre,
                null,
                novedadOrdenCargueEntidad.toContentValues());
    }
}