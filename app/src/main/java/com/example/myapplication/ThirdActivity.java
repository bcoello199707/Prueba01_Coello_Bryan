package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etDividendo, etDivisor, etNum;
    private Button btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etNombre = findViewById(R.id.etNombre3);
        etApellido = findViewById(R.id.etApellido3);
        etDividendo = findViewById(R.id.etDividendo3);
        etDivisor = findViewById(R.id.etDivisor3);
        etNum = findViewById(R.id.etNum3);
        btnCerrar = findViewById(R.id.btnCerrar3);

        String nombre = getIntent().getStringExtra("nombre");
        String apellido = getIntent().getStringExtra("apellido");
        etNombre.setText(nombre);
        etApellido.setText(apellido);

        btnCerrar.setOnClickListener(v -> {
            int dividendo = 0;
            int divisor = 0;
            int num = 0;
            
            try {
                dividendo = Integer.parseInt(etDividendo.getText().toString());
                divisor = Integer.parseInt(etDivisor.getText().toString());
                num = Integer.parseInt(etNum.getText().toString());
            } catch (NumberFormatException ignored) {}

            Intent resultIntent = new Intent();
            resultIntent.putExtra("dividendo", dividendo);
            resultIntent.putExtra("divisor", divisor);
            resultIntent.putExtra("num", num);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
