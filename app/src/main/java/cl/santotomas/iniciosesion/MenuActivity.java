package cl.santotomas.iniciosesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btMenuPerfil = findViewById(R.id.btMenuPerfil);
        Button btMenuCerrar = findViewById(R.id.btMenuCerrar);
        Button btMenuLista = findViewById(R.id.btMenuLista);

        btMenuPerfil.setOnClickListener(this);
        btMenuCerrar.setOnClickListener(this);

        btMenuLista.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btMenuPerfil:
                    Intent perfilIntent = new Intent(MenuActivity.this, PerfilActivity.class);
                    startActivity(perfilIntent);
                break;
            case R.id.btMenuCerrar:
                    Intent mainIntent = new Intent( MenuActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                break;
            case R.id.btMenuLista:
                Intent listaIntent =  new Intent( MenuActivity.this, ListaActivity.class );
                startActivity(listaIntent);
        }
    }
}