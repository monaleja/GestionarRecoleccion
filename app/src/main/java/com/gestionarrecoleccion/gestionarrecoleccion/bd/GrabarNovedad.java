package com.gestionarrecoleccion.gestionarrecoleccion.bd;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gestionarrecoleccion.gestionarrecoleccion.R;

/**
 * Created by Alejandra on 16/12/2016.
 */

public class GrabarNovedad extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_lista_ordencargue);
        //crearNovedad();
        //consultarNovedad();
    }

    public void crearNovedad(String usuarioCodigo, String cencosCodigo, String ordenCargue, String tipnovCodigo, String observacion)
    {
        //Abrimos la base de datos 'BdRedetrans' en modo escritura
        TbItemnovedadordencargue BdRedetrans = new TbItemnovedadordencargue(this, "bd_redetrans", null, 2);
        SQLiteDatabase bd = BdRedetrans.getWritableDatabase();
        SQLiteDatabase bdConsulta = BdRedetrans.getReadableDatabase();

        if(bd != null){
            //Insertamos 1 registro de ejemplo
            for(int i=1; i<=1; i++)
            {
                //Generamos los datos
                String ordcar_codigo = "01001109856";
                String mensaje = "probando sqlite";
                int tipnov_codigo = 5;
                String fecha = "2016-12-17";
                String hora = "19:41:12";
                int usuario_codigo = 2737;

                //Insertamos los datos en la tabla Usuarios
                bd.execSQL("INSERT INTO tb_itemnovedadordencargue " +
                        "VALUES (3,'"+ordcar_codigo+"','"+mensaje+"',"+tipnov_codigo+",'"+fecha+"','"+hora+"',"+usuario_codigo+",'NO');");
                //ordcar_codigo,itenovordcar_mensaje,tipnov_codigo,itenovordcar_fechacreacion,itenovordcar_horacreacion,usuario_codigo
                Toast.makeText(getApplicationContext(),"Se creo registro",Toast.LENGTH_SHORT).show();
            }
            //Cerramos la base de datos
            bd.close();
        }else{
            Toast.makeText(getApplicationContext(),"No existe base de datos",Toast.LENGTH_LONG).show();
        }
    }

    public void consultarNovedad()
    {
        //Abrimos la base de datos 'BdRedetrans' en modo lectura
        TbItemnovedadordencargue BdRedetrans = new TbItemnovedadordencargue(this, "bd_redetrans", null, 2);
        Toast.makeText(getApplicationContext(),"consultar novedad",Toast.LENGTH_SHORT).show();
        SQLiteDatabase bd = BdRedetrans.getReadableDatabase();

        //bd.execSQL("delete from tb_itemnovedadordencargue");
        Cursor c = bd.rawQuery(" SELECT * FROM tb_itemnovedadordencargue ", null);

        while(c.moveToNext()){
            Toast.makeText(getApplicationContext(),"Orden cargue: "+c.getString(1)+" Fecha: "+c.getString(4),Toast.LENGTH_SHORT).show();
            Log.d("Codigo ",c.getString(0));
            Log.d("Orden cargue ",c.getString(1));
            Log.d("Tipo novedad ",c.getString(3));
            Log.d("Hora ",c.getString(5));
            Log.d("Sincronizado ",c.getString(7));
            Toast.makeText(getApplicationContext(),"Si hay datos",Toast.LENGTH_SHORT).show();
        }

        if(!c.moveToNext()){
            Toast.makeText(getApplicationContext(),"No hay datos",Toast.LENGTH_SHORT).show();
        }
    }
}