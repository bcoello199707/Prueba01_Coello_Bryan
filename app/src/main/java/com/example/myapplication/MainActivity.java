package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etNumeroUno, etNumeroDos, etMultiplicacion, etPotencia, etFactorial;
    private Button btnSiguiente, btnResultados;

    private String nombre, apellido;
    private int numeroUno, numeroDos;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    nombre = data.getStringExtra("nombre");
                    apellido = data.getStringExtra("apellido");
                    numeroUno = data.getIntExtra("numeroUno", 0);
                    numeroDos = data.getIntExtra("numeroDos", 0);

                    etNombre.setText(nombre);
                    etApellido.setText(apellido);
                    etNumeroUno.setText(String.valueOf(numeroUno));
                    etNumeroDos.setText(String.valueOf(numeroDos));

                    btnResultados.setEnabled(true);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etNumeroUno = findViewById(R.id.etNumeroUno);
        etNumeroDos = findViewById(R.id.etNumeroDos);
        etMultiplicacion = findViewById(R.id.etMultiplicacion);
        etPotencia = findViewById(R.id.etPotencia);
        etFactorial = findViewById(R.id.etFactorial);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnResultados = findViewById(R.id.btnResultados);

        btnSiguiente.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            launcher.launch(intent);
        });

        btnResultados.setOnClickListener(v -> {
            // Multiplicación usando solo sumas: numeroUno * numeroDos
            int multiplicacion = 0;
            for (int i = 0; i < numeroDos; i++) {
                multiplicacion += numeroUno;
            }
            etMultiplicacion.setText(String.valueOf(multiplicacion));

            // Potencia usando solo sumas: numeroUno ^ numeroDos
            int potencia = 1;
            for (int i = 0; i < numeroDos; i++) {
                int tempPotencia = 0;
                for (int j = 0; j < numeroUno; j++) {
                    tempPotencia += potencia;
                }
                potencia = tempPotencia;
            }
            etPotencia.setText(String.valueOf(potencia));

            // Factorial usando solo sumas: numeroUno!
            int factorial = 1;
            for (int i = 1; i <= numeroUno; i++) {
                int tempFactorial = 0;
                for (int j = 0; j < i; j++) {
                    tempFactorial += factorial;
                }
                factorial = tempFactorial;
            }
            etFactorial.setText(String.valueOf(factorial));
        });
    }
}
