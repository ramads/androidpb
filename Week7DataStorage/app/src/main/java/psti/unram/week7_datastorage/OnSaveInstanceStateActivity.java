package psti.unram.week7_datastorage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class OnSaveInstanceStateActivity extends AppCompatActivity {

    private EditText etNim, etNama;
    private TextView result;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNim = findViewById(R.id.et_nim);
        etNama = findViewById(R.id.et_nama);
        result = findViewById(R.id.tv_result);
        button = findViewById(R.id.btn_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = getInputString();

                if (data != null) {
                    result.setText(data);
                }
            }
        });
    }

    protected String getInputString() {
        String nim = etNim.getText().toString();
        String nama = etNama.getText().toString();
        if (nim != "") {
            return "NIM: " + nim + "\t" + "Nama: " + nama;
        }
        return null;

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String data = getInputString();
        if (data != null) {
            outState.putString("data", data);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String data = savedInstanceState.getString("data");

        if (data != null) {
            result.setText(data);
        }

    }
}
