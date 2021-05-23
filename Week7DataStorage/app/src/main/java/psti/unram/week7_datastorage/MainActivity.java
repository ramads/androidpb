package psti.unram.week7_datastorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etNim, etNama;
    private TextView result;
    private Button button;

    private DataPref dataPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataPref = new DataPref(this);

        etNim = findViewById(R.id.et_nim);
        etNama = findViewById(R.id.et_nama);
        result = findViewById(R.id.tv_result);
        button = findViewById(R.id.btn_save);

        DataModel dataModel = dataPref.getPref();

        if (dataModel.isAvailable()) {
            result.setText("Nim: " + dataModel.getNim() + "\t" + "Nama: " + dataModel.getNama());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataModel dataModel = new DataModel();
                dataModel.setNim(etNim.getText().toString());
                dataModel.setNama(etNama.getText().toString());

                result.setText("Nim: " + dataModel.getNim() + "\t" + "Nama: " + dataModel.getNama());
                dataPref.savePref(dataModel);
            }
        });
    }
}
