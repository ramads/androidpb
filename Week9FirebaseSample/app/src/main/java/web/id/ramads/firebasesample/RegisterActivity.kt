package web.id.ramads.firebasesample

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var progressDialog: ProgressDialog

    private lateinit var auth: FirebaseAuth

    // static object
    private companion object {
        private const val TAG = "___TEST___"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this@RegisterActivity)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val etEmail = findViewById<TextView>(R.id.reg_email)
        val etPassword = findViewById<TextView>(R.id.reg_password)

        // action saat button register di klik
        btnRegister.setOnClickListener {
            if (etEmail.text.isEmpty()) {
                etEmail.error = "Email tidak boleh kosong!"
            } else if (etPassword.text.isEmpty()) {
                etPassword.error = "Password tidak boleh kosong!"
            } else {
                registerUserToFirebase(etEmail.text.toString(), etPassword.text.toString())
            }
        }
    }

    private fun registerUserToFirebase(email: String, password: String) {
        showProgressDialog()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    this.redirectToLoginScreen()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
                hideProgressDialog()
            }
    }

    private fun redirectToLoginScreen() {
        startActivity(Intent(baseContext, LoginActivity::class.java))
    }

    private fun showProgressDialog() {
        progressDialog.setTitle("Register")
        progressDialog.setMessage("Please wait..")
        progressDialog.show()
    }

    private fun hideProgressDialog() {
        progressDialog.hide()
    }
}