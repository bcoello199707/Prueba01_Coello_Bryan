package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etDividendo, etDivisor, etParteEntera, etResiduo, etNumInvertido;
    private Button btnSiguiente, btnResultados;

    private String nombre, apellido;
    private int dividendo, divisor, num;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    nombre = data.getStringExtra("nombre");
                    apellido = data.getStringExtra("apellido");
                    dividendo = data.getIntExtra("dividendo", 0);
                    divisor = data.getIntExtra("divisor", 0);
                    num = data.getIntExtra("num", 0);

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
        etDividendo = findViewById(R.id.etDividendo);
        etDivisor = findViewById(R.id.etDivisor);
        etParteEntera = findViewById(R.id.etParteEntera);
        etResiduo = findViewById(R.id.etResiduo);
        etNumInvertido = findViewById(R.id.etNumInvertido);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnResultados = findViewById(R.id.btnResultados);

        btnSiguiente.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            launcher.launch(intent);
        });

        btnResultados.setOnClickListener(v -> {
            etNombre.setText(nombre);
            etApellido.setText(apellido);
            etDividendo.setText(String.valueOf(dividendo));
            etDivisor.setText(String.valueOf(divisor));

            // Division and Residuo using only additions/subtractions
            int cociente = 0;
            int residuo = dividendo;
            if (divisor > 0) {
                while (residuo >= divisor) {
                    residuo -= divisor;
                    cociente++;
                }
            } else if (divisor < 0) {
                // Handling negative divisor if necessary, but assuming positive for simplicity
            }

            etParteEntera.setText(String.valueOf(cociente));
            etResiduo.setText(String.valueOf(residuo));

            // Invert number mathematically using only additions/subtractions for division/modulo logic
            int invertido = 0;
            int auxNum = num;
            while (auxNum > 0) {
                int q = 0;
                int r = auxNum;
                // Find auxNum / 10 and auxNum % 10 using subtractions
                while (r >= 10) {
                    r -= 10;
                    q++;
                }
                // r is now auxNum % 10, q is now auxNum / 10
                
                // invertido = (invertido * 10) + r using only additions
                int tempInvertido = 0;
                for (int i = 0; i < 10; i++) {
                    tempInvertido += invertido;
                }
                invertido = tempInvertido + r;
                auxNum = q;
            }
            etNumInvertido.setText(String.valueOf(invertido));
        });
    }
}
