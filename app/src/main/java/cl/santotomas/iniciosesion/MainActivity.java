package cl.santotomas.iniciosesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_login = findViewById(R.id.btMainIngresar);
        TextView lnk_registrar = findViewById(R.id.tvMainRegistro);

        btn_login.setOnClickListener(this);
        lnk_registrar.setOnClickListener(this);

        Log.i(TAG, "Estamos en la funcion OnCreate");
        Toast.makeText(this, "Aqui el mensaje", Toast.LENGTH_LONG).show();  //NOTIFICAR AL USUARIO
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btMainIngresar:
                    Toast.makeText(this, "Se oprimio el Boton ingresar", Toast.LENGTH_LONG).show();
                break;
            case R.id.tvMainRegistro:

                    Intent registroIntent = new Intent(MainActivity.this, RegistroActivity.class);
                    startActivity(registroIntent);
                    // Toast.makeText(this, "Se oprimio", Toast.LENGTH_LONG).show();
                break;
        }
    }

}