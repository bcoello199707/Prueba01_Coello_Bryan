package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etDividendo, etDivisor, etNum;
    private Button btnSiguiente, btnCerrar;
    private String nombre, apellido;
    private int dividendo, divisor, num;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    dividendo = data.getIntExtra("dividendo", 0);
                    divisor = data.getIntExtra("divisor", 0);
                    num = data.getIntExtra("num", 0);

                    etDividendo.setText(String.valueOf(dividendo));
                    etDivisor.setText(String.valueOf(divisor));
                    etNum.setText(String.valueOf(num));

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
        etDividendo = findViewById(R.id.etDividendo2);
        etDivisor = findViewById(R.id.etDivisor2);
        etNum = findViewById(R.id.etNum2);
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
            Intent resultIntent = new Intent();
            resultIntent.putExtra("nombre", nombre);
            resultIntent.putExtra("apellido", apellido);
            resultIntent.putExtra("dividendo", dividendo);
            resultIntent.putExtra("divisor", divisor);
            resultIntent.putExtra("num", num);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
