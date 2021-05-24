package web.id.ramads.firebasesample

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


class DataActivity : AppCompatActivity() {
    private lateinit var progressDialog: ProgressDialog
    private val adapter = DataAdapter()
    private val data = ArrayList<String>()

    var db = FirebaseFirestore.getInstance()

    private companion object {
        private const val TAG = "___TEST___"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        progressDialog = ProgressDialog(this@DataActivity)

        data.add("Data Masih Kosong")
        adapter.setData(data);

        val list = findViewById<RecyclerView>(R.id.rv_data)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter


        // get data from firebase firestore
        this.getDataFromFirestore()
    }

    private fun getDataFromFirestore() {
        showProgressDialog()
        db.collection("mahasiswa")
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    data.clear()
                    for (document in task.result!!) {
                        var temp : String = document.data["nim"] as String
                        temp = temp + " - " + document.data["nama"] as String

                        Log.d(TAG, document.id + " => " + document.data)

                        data.add(temp)
                    }

                    adapter.setData(data)
                    hideProgressDialog()
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                    hideProgressDialog()
                }
            })
    }

    private fun showProgressDialog() {
        progressDialog.setTitle("Fetching Data")
        progressDialog.setMessage("Please wait..")
        progressDialog.show()
    }

    private fun hideProgressDialog() {
        progressDialog.hide()
    }
}