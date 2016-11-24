package com.gestionarrecoleccion.gestionarrecoleccion;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

/**
 * Created by Alejandra on 23/11/2016.
 */

public class Login extends AppCompatActivity implements Validator.ValidationListener{
    @NotEmpty(message = "Digite su email" )
    EditText etEmail;
    @NotEmpty(message = "Digite su clave" )
    EditText etClave;
    Validator validator;
    CheckBox cbRecordarme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //this.initComponents();
    }

    /**
     * Método para inicializar los componentes.
     */
    private void initComponents(){
        //Miscelanea.verificarConexion(getApplicationContext(), this);

        //validarSesion();

        validator = new Validator(this);
        validator.setValidationListener(this);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etClave = (EditText) findViewById(R.id.etClave);
        cbRecordarme = (CheckBox) findViewById(R.id.cbRecordarme);
    }


    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> list) {

    }
}
