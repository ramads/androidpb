package web.id.ramads.firebasesample

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class FormActivity : AppCompatActivity() {
    private lateinit var progressDialog: ProgressDialog
    private lateinit var auth: FirebaseAuth

    var db = FirebaseFirestore.getInstance()

    private companion object {
        private const val TAG = "___TEST___"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        progressDialog = ProgressDialog(this@FormActivity)


        val btnInsert: Button = findViewById(R.id.btnInsert);
        val btnRead: Button = findViewById(R.id.btnRead);
        val etNim: EditText = findViewById(R.id.et_nim);
        val etNama: EditText = findViewById(R.id.et_nama);


        btnRead.setOnClickListener {
            startActivity(Intent(this@FormActivity, DataActivity::class.java))
        }

        btnInsert.setOnClickListener {
            if (etNim.text.isEmpty()) {
                etNim.error = "NIM tidak boleh kosong!"
            } else if (etNama.text.isEmpty()) {
                etNama.error = "Nama tidak boleh kosong!"
            } else {
                addNewDataToFirestore(etNim.text.toString(), etNama.text.toString())
            }
        }

        auth = Firebase.auth
    }

    private fun addNewDataToFirestore(nim: String, nama: String) {
        showProgressDialog()
        val mahasiswa: MutableMap<String, Any> = HashMap()
        mahasiswa["uid"] = auth.currentUser!!.uid
        mahasiswa["nim"] = nim
        mahasiswa["nama"] = nama
        db.collection("mahasiswa")
            .add(mahasiswa)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG,
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
                hideProgressDialog()
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e)
                hideProgressDialog()
            }
    }

    private fun showProgressDialog() {
        progressDialog.setTitle("Insert Data")
        progressDialog.setMessage("Please wait..")
        progressDialog.show()
    }

    private fun hideProgressDialog() {
        progressDialog.hide()
    }
}