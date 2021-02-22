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
        EditText editText = findViewById(R.id.et_input);
        TextView tvHasil = findViewById(R.id.tv_hasil);
        tvHasil.setText(editText.getText().toString());

    }
}