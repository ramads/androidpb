package unram.psti.week4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Sub2Activity extends AppCompatActivity {

    public static String EXTRA_DATA = "data";


    private String receivedData = null;
    private TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        txtData = findViewById(R.id.txt_data);


        Mahasiswa receivedData = getIntent().getParcelableExtra(EXTRA_DATA);

        receivedData.setNama("Ilham");

//        String mahasiwa = "nama : " + receivedData.getNama() + "\t" + "nim: " + receivedData.getNim();

        Mahasiswa receivedData2 = getIntent().getParcelableExtra(EXTRA_DATA);

        String mahasiwa2 = "nama : " + receivedData2.getNama() + "\t" + "nim: " + receivedData2.getNim();

        txtData.setText(mahasiwa2);


    }
}
