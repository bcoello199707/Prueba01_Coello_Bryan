package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etNumeroUno, etNumeroDos;
    private Button btnSiguiente, btnCerrar;
    private String nombre, apellido;
    private int numeroUno, numeroDos;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    numeroUno = data.getIntExtra("numeroUno", 0);
                    numeroDos = data.getIntExtra("numeroDos", 0);

                    etNumeroUno.setText(String.valueOf(numeroUno));
                    etNumeroDos.setText(String.valueOf(numeroDos));

                    // Activar para ingresar nombres y apellidos solo cuando viene de la tercera pantalla
                    etNombre.setEnabled(true);
                    etApellido.setEnabled(true);
                    btnCerrar.setEnabled(true);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etNombre = findViewById(R.id.etNombre2);
        etApellido = findViewById(R.id.etApellido2);
        etNumeroUno = findViewById(R.id.etNumeroUno2);
        etNumeroDos = findViewById(R.id.etNumeroDos2);
        btnSiguiente = findViewById(R.id.btnSiguiente2);
        btnCerrar = findViewById(R.id.btnCerrar2);

        btnSiguiente.setOnClickListener(v -> {
            nombre = etNombre.getText().toString();
            apellido = etApellido.getText().toString();
            
            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellido", apellido);
            launcher.launch(intent);
        });

        btnCerrar.setOnClickListener(v -> {
            nombre = etNombre.getText().toString();
            apellido = etApellido.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("nombre", nombre);
            resultIntent.putExtra("apellido", apellido);
            resultIntent.putExtra("numeroUno", numeroUno);
            resultIntent.putExtra("numeroDos", numeroDos);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
