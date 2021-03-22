package unram.psti.week5_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SimpleListViewActivity extends AppCompatActivity {

    private ListView listView;

    private String[] footballClubs = {
            "Juventus",
            "Ac Milan",
            "Manchester United",
            "Liverpool",
            "Bayern Munchen",
            "Real Madrid",
            "Barcelona",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.list_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.simple_list_item,
                R.id.simple_text,
                footballClubs
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(
                        SimpleListViewActivity.this,
                        "Data yang di klik: " + footballClubs[i],
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
