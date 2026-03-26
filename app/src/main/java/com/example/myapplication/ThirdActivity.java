package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etNumeroUno, etNumeroDos;
    private Button btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etNombre = findViewById(R.id.etNombre3);
        etApellido = findViewById(R.id.etApellido3);
        etNumeroUno = findViewById(R.id.etNumeroUno3);
        etNumeroDos = findViewById(R.id.etNumeroDos3);
        btnCerrar = findViewById(R.id.btnCerrar3);

        String nombre = getIntent().getStringExtra("nombre");
        String apellido = getIntent().getStringExtra("apellido");
        etNombre.setText(nombre);
        etApellido.setText(apellido);

        btnCerrar.setOnClickListener(v -> {
            String sNum1 = etNumeroUno.getText().toString();
            String sNum2 = etNumeroDos.getText().toString();

            if (sNum1.isEmpty() || sNum2.isEmpty()) {
                Toast.makeText(this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int num1 = Integer.parseInt(sNum1);
                int num2 = Integer.parseInt(sNum2);

                if (num1 <= 0 || num2 <= 0) {
                    Toast.makeText(this, "Los números no pueden ser ceros ni negativos", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("numeroUno", num1);
                resultIntent.putExtra("numeroDos", num2);
                setResult(RESULT_OK, resultIntent);
                finish();

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Ingrese números válidos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
