package unram.psti.week5_listview;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AbsListView view;
    ArrayList<Club> dataClubs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataClubs = createData();

        // default
        showGridView();

        // action on click
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(
                        MainActivity.this,
                        "Data yang di klik: " + dataClubs.get(i).getName(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    private ArrayList<Club> createData() {
        String[] dataClubName = getResources().getStringArray(R.array.club_name);
        String[] dataClubInfo = getResources().getStringArray(R.array.club_info);
        TypedArray dataClubImage = getResources().obtainTypedArray(R.array.club_image);

        ArrayList<Club> list = new ArrayList<>();
        for(int i = 0; i < dataClubInfo.length; i++) {
            Club club = new Club();
            club.setName(dataClubName[i]);
            club.setInfo(dataClubInfo[i]);
            club.setImage(dataClubImage.getResourceId(i, -1));

            list.add(club);
        }

        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setViewMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void showListView(){
        setContentView(R.layout.activity_list_view);
        view = findViewById(R.id.list_view);

        CustomAdapterList adapter = new CustomAdapterList(this);
        view.setAdapter(adapter);
        adapter.setClubs(dataClubs);
    }

    private void showGridView(){
        setContentView(R.layout.activity_grid_view);
        view = findViewById(R.id.gridView);

        CustomAdapterGrid adapter = new CustomAdapterGrid(this);
        view.setAdapter(adapter);
        adapter.setClubs(dataClubs);
    }

    public void setViewMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_list:
                showListView();
                break;

            case R.id.action_grid:
                showGridView();
                break;
        }
    }
}
