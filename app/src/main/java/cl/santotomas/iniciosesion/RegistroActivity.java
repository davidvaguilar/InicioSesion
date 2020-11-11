package cl.santotomas.iniciosesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cl.santotomas.iniciosesion.modelo.Usuario;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etRegistroNombre;
    private EditText etRegistroEmail;
    private EditText etRegistroClave;
    private EditText etRegistroRepetir;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        this.etRegistroNombre = findViewById(R.id.etRegistroNombre);
        this.etRegistroEmail = findViewById(R.id.etRegistroEmail);
        this.etRegistroClave = findViewById(R.id.etRegistroClave);
        this.etRegistroRepetir = findViewById(R.id.etRegistroRepetir);

        this.usuario = new Usuario();

        Button btRegistroConfirmar = findViewById(R.id.btRegistroConfirmar);
        btRegistroConfirmar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btRegistroConfirmar:

                    this.usuario.setNombre(this.etRegistroNombre.getText().toString());
                    this.usuario.setEmail(this.etRegistroEmail.getText().toString());
                    this.usuario.setPassword(this.etRegistroClave.getText().toString());

                    String repetir = this.etRegistroRepetir.getText().toString();


                    if( !this.usuario.esVacio() ){
                        if( this.etRegistroClave.getText().toString().length() >= 4){
                            if( this.usuario.claveIgual(repetir) ){

                                buscar_usuario();


                                // SI USUARIO YA ESTA REGISTRADO
                                // Consulta

                                if( registrar_usuario() > 0){
                                    this.etRegistroNombre.setText("");
                                    this.etRegistroEmail.setText("");
                                    this.etRegistroClave.setText("");
                                    this.etRegistroRepetir.setText("");

                                    Toast.makeText(this, "Registro del usuario correcto", Toast.LENGTH_LONG).show();
                                    // ELIMINAR este ACTIVIDAD
                                } else {
                                    Toast.makeText(this, "Error al guardar en la Base de Datos", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(this, "Clave no es correcta", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(this, "La cantidad minima de caracteres son 5.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
                    }

                    //Toast.makeText(this, "Boton Registro se oprimio", Toast.LENGTH_LONG).show();
                break;
        }

    }

    private long registrar_usuario(){
        AdminSQLiteOpenHelper base = new AdminSQLiteOpenHelper(this,"administracion1", null, 1);
        SQLiteDatabase baseDatos = base.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("nombre", this.usuario.getNombre());
        registro.put("email", this.usuario.getEmail());
        registro.put("clave", this.usuario.getPassword());

        long resultado = baseDatos.insert("usuarios", null, registro);
        baseDatos.close();

        Log.i("PruebaRegistro", String.valueOf(resultado) );
        return resultado;
    }

    // Funcion Validar Correo ya ingresado
    private boolean buscar_usuario(){
        AdminSQLiteOpenHelper base = new AdminSQLiteOpenHelper(this,"administracion1", null, 1);
        SQLiteDatabase baseDatos = base.getWritableDatabase();

        Cursor filas = baseDatos.rawQuery(
                "SELECT COUNT(*) FROM usuarios WHERE email ="+ this.usuario.getEmail(),null
        );

        if( filas.moveToFirst() ){
            String cantidad = filas.getString(0);
            Log.i("LOGPRUEBA", cantidad);
            return true;
        } else {
            Log.i("LOGPRUEBA", "NO existen valores igual a "+this.usuario.getEmail());
            return false;
        }

    }
}