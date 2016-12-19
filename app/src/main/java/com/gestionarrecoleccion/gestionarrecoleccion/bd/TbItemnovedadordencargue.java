package com.gestionarrecoleccion.gestionarrecoleccion.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alejandra on 16/12/2016.
 */

public class TbItemnovedadordencargue extends SQLiteOpenHelper{

    //Sentencia SQL para crear la tabla de tb_itemnovedadordencargue
    String sqlCrear = "CREATE TABLE tb_itemnovedadordencargue( " +
                     "itenovordcar_codigo INT PRIMARY KEY NOT NULL, " +
                     "ordcar_codigo TEXT NOT NULL, " +
                     "itenovordcar_mensaje TEXT NOT NULL," +
                     "tipnov_codigo INT NOT NULL," +
                     "itenovordcar_fechacreacion TEXT NOT NULL," +
                     "itenovordcar_horacreacion TEXT NOT NULL," +
                     "usuario_codigo INT NOT NULL," +
                     "sincronizado TEXT"+
                     ")";

    public TbItemnovedadordencargue(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd)
    {
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
        bd.execSQL("DROP TABLE IF EXISTS tb_itemnovedadordencargue");

        //Se crea la nueva versión de la tabla
        bd.execSQL(sqlCrear);
    }
}