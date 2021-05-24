package web.id.ramads.firebasesample

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var progressDialog: ProgressDialog

    private lateinit var btnFillForm: Button
    private lateinit var btnLogout: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    private companion object {
        private const val TAG = "___TEST___"
        private const val RC_SIGN_IN = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressDialog = ProgressDialog(this@LoginActivity)

        val etEmail = findViewById<TextView>(R.id.et_email)
        val etPassword = findViewById<TextView>(R.id.et_password)
        val btnRegister = findViewById<Button>(R.id.buttonRegister)
        val btnLoginEmail = findViewById<Button>(R.id.button)
        val btnLoginGoogle = findViewById<Button>(R.id.buttonGoogle)

        btnLogout = findViewById(R.id.buttonLogout)
        btnFillForm = findViewById(R.id.buttonFillForm)

        // saat button register ditekan, pindah ke halaman register
        btnRegister.setOnClickListener {
            startActivity(Intent(baseContext, RegisterActivity::class.java))
        }

        // sign out user saat button logout ditekan
        btnLogout.setOnClickListener {
            auth.signOut()
            updateUI()
        }

        // saat button isi data ditekan, pindah ke halaman form isi data
        btnFillForm.setOnClickListener {
            startActivity(Intent(baseContext, FormActivity::class.java))
        }

        btnLoginEmail.setOnClickListener {
            if (etEmail.text.isEmpty()) {
                etEmail.error = "Email tidak boleh kosong!"
            } else if (etPassword.text.isEmpty()) {
                etPassword.error = "Password tidak boleh kosong!"
            } else {
                signInWithEmailFirebase(etEmail.text.toString(), etPassword.text.toString())
            }
        }

        btnLoginGoogle.setOnClickListener {
            signInWithGoogleFirebase()
        }

        auth = Firebase.auth

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // setup ui saat halaman login muncul, hide or show button untuk user yang telah login
        updateUI()
    }

    private fun signInWithEmailFirebase(email:String, password:String){
        this.showProgressDialog()
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser


                        this.updateUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        this.updateUI()
                    }

                    hideProgressDialog()
                }
    }

    private fun signInWithGoogleFirebase() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = auth.currentUser
                        updateUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        updateUI()
                    }
                }
    }

    // cek, jika user telah login tampilkan button isi data dan logout
    private fun updateUI() {
        Log.d(TAG, auth.currentUser!!.email.toString())
        Log.d(TAG, auth.currentUser!!.displayName.toString())
        Log.d(TAG, auth.currentUser!!.phoneNumber.toString())

        if (auth.currentUser != null) {
            btnLogout.visibility = View.VISIBLE
            btnFillForm.visibility = View.VISIBLE
        } else {
            btnLogout.visibility = View.GONE
            btnFillForm.visibility = View.GONE
        }
    }

    private fun showProgressDialog() {
        progressDialog.setTitle("Login with email")
        progressDialog.setMessage("Please wait..")
        progressDialog.show()
    }

    private fun hideProgressDialog() {
        progressDialog.hide()
    }
}