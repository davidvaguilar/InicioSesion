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
import android.widget.Toast;

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

        buscar_usuario("david@gmail.com");   // 1234

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

    private void buscar_usuario(String correo){
        AdminSQLiteOpenHelper base = new AdminSQLiteOpenHelper(this,"administracion2", null, 1);
        SQLiteDatabase baseDatos = base.getWritableDatabase();

String sql = "SELECT nombre, telefono, clave, email FROM usuarios WHERE email ='"+correo+"';";
        Cursor filas = baseDatos.rawQuery(
                sql,null
        );
        try {
            filas.moveToFirst();
            this.usuario.setNombre(filas.getString(0));
            this.usuario.setTelefono(filas.getString(1));
            this.usuario.setPassword(filas.getString(2));

            this.usuario.setEmail(filas.getString(3));
            Log.i("LOGPRUEBA1", this.usuario.toString());
        } catch(Exception e){
            Log.i("LOGPRUEBA1", e.getMessage());
        }


    }

    private void actualizar(){
        AdminSQLiteOpenHelper base = new AdminSQLiteOpenHelper(this,"administracion2", null, 1);
        SQLiteDatabase baseDatos = base.getWritableDatabase();

        this.usuario.setNombre(this.etActualizarNombre.getText().toString());
        this.usuario.setTelefono(this.etActualizarTelefono.getText().toString());
        this.usuario.setPassword(this.etActualizarClave.getText().toString());

        Log.i("LOGPRUEBA", String.valueOf(this.usuario.getNombre().length()));
        if( !this.usuario.esVacio() ) {
            if( this.etActualizarClave.getText().toString().length() >= 4) {

                ContentValues registro = new ContentValues();
                registro.put("nombre", this.usuario.getNombre());
                registro.put("telefono", this.usuario.getTelefono());
                registro.put("clave", this.usuario.getPassword());
                String condicion = "email = '" + this.usuario.getEmail() + "'";

                int resultado = baseDatos.update("usuarios", registro, condicion, null);

                Log.i("LOGPRUEBA", String.valueOf(resultado));
                baseDatos.close();

                if (resultado == 1) {
                    Toast.makeText(this, "Datos actualizados", Toast.LENGTH_LONG).show();
                    finish();    // OK
                }
            }else {
                Log.i("LOGPRUEBA", "La cantidad minima de caracteres son 5.");
                Toast.makeText(this, "La cantidad minima de caracteres son 5.", Toast.LENGTH_LONG).show();
            }
            // PORQUE HAY UN ERROR
        }else {
            Log.i("LOGPRUEBA", "Todos los campos son obligatorios");
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
        }
    }
}