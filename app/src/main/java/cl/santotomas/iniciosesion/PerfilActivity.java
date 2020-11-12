package cl.santotomas.iniciosesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cl.santotomas.iniciosesion.modelo.Usuario;

public class PerfilActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etActualizarNombre;
    private EditText etActualizarTelefono;
    private EditText etActualizarClave;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        this.etActualizarNombre = findViewById(R.id.etActualizarNombre);
        this.etActualizarTelefono = findViewById((R.id.etActualizarTelefono));
        this.etActualizarClave = findViewById(R.id.etActualizarClave);

        this.usuario = new Usuario();

        this.etActualizarNombre.setText(this.usuario.getNombre());
        this.etActualizarTelefono.setText(this.usuario.getTelefono());
        this.etActualizarClave.setText(this.usuario.getPassword());

        Button btActualizarConfirmar = findViewById(R.id.btActualizarConfirmar);
        btActualizarConfirmar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btActualizarConfirmar:
                                    actualizar();
                break;
        }
    }

    private boolean buscar_usuario(){
        AdminSQLiteOpenHelper base = new AdminSQLiteOpenHelper(this,"administracion1", null, 1);
        SQLiteDatabase baseDatos = base.getWritableDatabase();

        Cursor filas = baseDatos.rawQuery(
                "SELECT nombre, telefono, clave FROM usuarios WHERE email ='david@gmail.com';",null
        );

        filas.moveToFirst();
        int cantidad = filas.getInt(0);

        this.usuario.setNombre(filas.getString(0));
        this.usuario.setTelefono(filas.getString(1));
        this.usuario.setPassword(filas.getString(2));

        if( cantidad > 0 ){
            this.usuario.setNombre(filas.getString(0));
            this.usuario.setTelefono(filas.getString(1));
            this.usuario.setPassword(filas.getString(2));
            return true;     // usuario encontrado
        } else {
            return false;   // No existen usuarios
        }

    }

    private boolean actualizar(){
        AdminSQLiteOpenHelper base = new AdminSQLiteOpenHelper(this,"administracion1", null, 1);
        SQLiteDatabase baseDatos = base.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("nombre", this.usuario.getNombre());
        registro.put("telefono", this.usuario.getTelefono());
        registro.put("clave", this.usuario.getPassword());

        String condicion = "email = "+this.usuario.getEmail();

        int resultado = baseDatos.update("usuarios", registro, condicion, null);
        Log.i("LOGPRUEBA" , String.valueOf(resultado));
        baseDatos.close();
        return true;
    }
}