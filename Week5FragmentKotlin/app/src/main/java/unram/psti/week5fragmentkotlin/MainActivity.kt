package unram.psti.week5fragmentkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // menambahkan fragment pada main activity
        // instansiasi fragment manager
        val mFragmentManager = supportFragmentManager
        val mHomeFragment = HomeFragment()
        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

        // transaski fragment
        if (fragment !is HomeFragment) {
            Log.d("FragmentApp", "Fragment Name :" + HomeFragment::class.java.simpleName)
            mFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, mHomeFragment, HomeFragment::class.java.simpleName)
                .commit()
        }
    }
}