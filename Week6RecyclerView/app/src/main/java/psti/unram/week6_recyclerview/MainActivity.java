package psti.unram.week6_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvHeroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvHeroes = findViewById(R.id.rv_heroes);

        // untuk keperluan optimasi
        rvHeroes.setHasFixedSize(true);

        // set layout manager
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Hero> list = getListHeroes();

        RvHeroAdapter adapter = new RvHeroAdapter(list);
        rvHeroes.setAdapter(adapter);

        adapter.setOnRvItemClickListener(new RvHeroAdapter.OnRvItemClickListener() {
            @Override
            public void OnRvItemClick(View view, Hero hero) {
                Toast.makeText(
                  MainActivity.this,
                  "Data yang diklik: " + hero.getName(),
                  Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    public ArrayList<Hero> getListHeroes() {
        String[] dataName = getResources().getStringArray(R.array.data_name);
        String[] dataDesc = getResources().getStringArray(R.array.data_description);
        String[] dataPhoto = getResources().getStringArray(R.array.data_photo);

        ArrayList<Hero> list = new ArrayList<>();

        for (int i=0; i< dataName.length; i++) {
            Hero hero = new Hero();
            hero.setName(dataName[i]);
            hero.setDesc(dataDesc[i]);
            hero.setPhoto(dataPhoto[i]);
            list.add(hero);
        }

        return list;
    }
}
