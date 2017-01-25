package com.gestionarrecoleccion.gestionarrecoleccion.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.gestionarrecoleccion.gestionarrecoleccion.entidades.NovedadOrdenCargueEntidad;

/**
 * Created by Alejandra on 16/12/2016.
 */

public class TbItemnovedadordencargue extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bd_redetrans.db";

    public TbItemnovedadordencargue(Context context) {
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

        bd.delete(NovedadOrdenCargueEsquema.NovedadOrdenCargue.tablaNombre,
                NovedadOrdenCargueEsquema.NovedadOrdenCargue.novedadCodigo + "=" + 5,null);

        long query =  bd.insert(
                NovedadOrdenCargueEsquema.NovedadOrdenCargue.tablaNombre,
                null,
                novedadOrdenCargueEntidad.toContentValues());
        return query;
    }

    public void consultarNovedad(Context context){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor c = bd.rawQuery("select * from " + NovedadOrdenCargueEsquema.NovedadOrdenCargue.tablaNombre,null);

        /*Cursor c = bd.query(
                NovedadOrdenCargueEsquema.NovedadOrdenCargue.tablaNombre,  // Nombre de la tabla
                null,  // Lista de Columnas a consultar
                null,  // Columnas para la cláusula WHERE
                null,  // Valores a comparar con las columnas del WHERE
                null,  // Agrupar con GROUP BY
                null,  // Condición HAVING para GROUP BY
                null  // Cláusula ORDER BY
        );*/

        Log.d("Cantidad ", String.valueOf(c.getCount()));

        while(c.moveToNext()){
            //Toast.makeText(context,"Orden cargue: "+c.getString(1)+" Fecha: "+c.getString(4),Toast.LENGTH_SHORT).show();
            Log.d("Codigo ",c.getString(0));
            Log.d("Orden cargue ",c.getString(1));
            Log.d("Observacion ",c.getString(2));
            Log.d("Tipo novedad ",c.getString(3));
            Log.d("Fecha ",c.getString(4));
            Log.d("Hora ",c.getString(5));
            Log.d("Usuario ",c.getString(6));
            Log.d("Sincronizado ",c.getString(7));
            Toast.makeText(context,"Si hay datos",Toast.LENGTH_SHORT).show();
        }

        if(c.getCount() == 0){
            Toast.makeText(context,"No hay datos",Toast.LENGTH_SHORT).show();
        }
    }
}