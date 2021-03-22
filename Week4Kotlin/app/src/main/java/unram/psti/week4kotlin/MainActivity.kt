package unram.psti.week4kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSub1: Button = findViewById(R.id.btn_activity_sub_1)
        val btnSub2: Button = findViewById(R.id.btn_activity_sub_2)
//    val btnDial: Button = findViewById(R.id.btn_activity_dial)
//    val btnWeb: Button = findViewById(R.id.btn_activity_web)

        btnSub1.setOnClickListener(this);
        btnSub2.setOnClickListener(this);
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_activity_sub_1 -> {
                val moveIntent = Intent(this@MainActivity, Sub1Activity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_activity_sub_2 -> {
                val moveIntent = Intent(this@MainActivity, Sub2Activity::class.java)
                moveIntent.putExtra(Sub2Activity.EXTRA_NAME, "halo guys!")
                startActivity(moveIntent)
            }
        }
    }
}