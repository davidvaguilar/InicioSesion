package cl.santotomas.iniciosesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Estamos en la funcion OnCreate");
        Toast.makeText(this, "Aqui el mensaje", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Estamos en la funcion OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Estamos en la funcion OnResumen");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Estamos en la funcion OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Estamos en la funcion OnStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "Estamos en la funcion OnRestart");
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "Estamos en la funcion OnDestroy");
        super.onDestroy();

    }
}