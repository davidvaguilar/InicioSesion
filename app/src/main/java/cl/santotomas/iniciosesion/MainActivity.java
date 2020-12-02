package cl.santotomas.iniciosesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cl.santotomas.iniciosesion.modelo.Usuario;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private EditText etMainEmail;
    private EditText etMainClave;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.etMainEmail = findViewById(R.id.etMainEmail);
        this.etMainClave = findViewById(R.id.etMainClave);

        this.usuario = new Usuario();

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

                    this.usuario.setEmail(etMainEmail.getText().toString());
                    this.usuario.setPassword(etMainClave.getText().toString());
                    if (validar_usuario()) {
                        Intent menuIntent = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(menuIntent);
                        finish();
                    } else{
                        Toast.makeText(this, "Usuario o contraseña incorrecto", Toast.LENGTH_LONG).show();
                    }

                    //Toast.makeText(this, "Se oprimio el Boton ingresar", Toast.LENGTH_LONG).show();
                break;
            case R.id.tvMainRegistro:

                    Intent registroIntent = new Intent(MainActivity.this, RegistroActivity.class);
                    startActivity(registroIntent);
                    // Toast.makeText(this, "Se oprimio", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private boolean validar_usuario(){
        AdminSQLiteOpenHelper base = new AdminSQLiteOpenHelper(this, "administracion2", null, 1);
        SQLiteDatabase baseDatos = base.getWritableDatabase();

        Cursor filas =baseDatos.rawQuery(
                "SELECT COUNT(*) FROM usuarios " +
                        "WHERE email = '"+this.usuario.getEmail()+"' " +
                        "AND clave = '"+this.usuario.getPassword()+"';", null
        );
        filas.moveToFirst();
        int cantidad = filas.getInt(0);
        if( cantidad > 0 ){
            return true;
        }
        return false;
    }


}