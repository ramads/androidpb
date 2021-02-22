package unram.psti.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {

        // melakukan binding resource tampilan design pada layout/activity_main.xml
        
        // untuk komponen Edit Text, gunakan tipe data EditText, method findViewById digunakan untuk menemukan resource pada layout/activity_main.xml berdasarkan id yang telah diberikan
        EditText editText = findViewById(R.id.et_input);

        // untuk komponen Text View, gunakan tipe data TextView 
        TextView tvHasil = findViewById(R.id.tv_hasil);

        // set text yang didapatkan dari komponen edit text ke komponen text view hasil
        tvHasil.setText(editText.getText().toString());

    }
}