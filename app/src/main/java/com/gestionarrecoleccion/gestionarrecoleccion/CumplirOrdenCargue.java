package com.gestionarrecoleccion.gestionarrecoleccion;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gestionarrecoleccion.gestionarrecoleccion.modelos.OrdenCargueEntidad;

import java.util.ArrayList;

/**
 * Created by Alejandra on 03/12/2016.
 */

public class CumplirOrdenCargue extends AppCompatActivity {
    TextView tvUsuarioLogin;
    TextView tvUsuarioCentrocosto;
    TextView tvOrdenCargueCodigo;
    EditText etRemesa;
    EditText etPeso;
    EditText etCantidad;
    Spinner spRegionalDestino;
    Button btnGuardarCumplido;
    Button btnCancelarCumplido;
    SharedPreferences sharedPref;
    ArrayList<OrdenCargueEntidad> arrayOrdenescargue = new ArrayList<OrdenCargueEntidad>();
    //ArrayList<RegionalEntidad> objRegional = new ArrayList<RegionalEntidad>();
    //ArrayAdapter<RegionalEntidad> regionalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cumplir_ordencargue);
        //initComponents();
    }


}
