package unram.psti.week4kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Sub2Activity : AppCompatActivity() {
    companion object {
        const val EXTRA_NAME = "EXTRA_NAME"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub2)

        val txtData: TextView = findViewById(R.id.txt_data)

        val data = intent.getStringExtra(EXTRA_NAME)
        val str = "Data yang dikirim: $data"
        txtData.text = str

    }
}