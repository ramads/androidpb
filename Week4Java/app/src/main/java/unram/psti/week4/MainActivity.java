package unram.psti.week4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnSub1, btnSub2, btnDial, btnWeb;
    public Mahasiswa mahasiswa;


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("activity_lifecyle", "onStart jalan!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("activity_lifecyle", "onResume jalan!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("activity_lifecyle", "onPause jalan!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("activity_lifecyle", "onStop jalan!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("activity_lifecyle", "onDestroy jalan!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("activity_lifecyle", "onRestart jalan!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("activity_lifecyle", "onCreate jalan!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSub1 = findViewById(R.id.btn_activity_sub_1);
        btnSub2 = findViewById(R.id.btn_activity_sub_2);
        btnDial = findViewById(R.id.btn_activity_dial);
        btnWeb = findViewById(R.id.btn_activity_web);

        TextView tv = findViewById(R.id.textView);

        mahasiswa = new Mahasiswa();


        mahasiswa.setNama("Agung");
        mahasiswa.setNim("f1d017");

        tv.setText("nama : " + mahasiswa.getNama() + "\t" + "nim: " + mahasiswa.getNim());

        btnSub1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intentSub1 = new Intent(MainActivity.this, Sub1Activity.class);
                startActivity(intentSub1);
            }
        });

        btnSub2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Masukkan data yang akan dikirim");
                final EditText input = new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input)
                        .setPositiveButton("Kirim Data", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String data = input.getText().toString();
                                Intent intentSub2 = new Intent(MainActivity.this, Sub2Activity.class);

                                // kirim data
                                intentSub2.putExtra(Sub2Activity.EXTRA_DATA, mahasiswa);

                                startActivity(intentSub2);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Masukkan No HP Anda");
                final EditText input = new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input)
                        .setPositiveButton("Dial", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String noTelp = input.getText().toString();
                                Intent intentDial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+noTelp));

                                System.out.println("================================" + Uri.parse("tel:"+noTelp));
                                startActivity(intentDial);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com"));
                startActivity(intentWeb);
            }
        });
    }
}
