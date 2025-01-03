package com.shukhibhavtu.coaching_managmant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.shukhibhavtu.coaching_managmant.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private val binding : ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onStart() {
        super.onStart()
        // CHECK IF USER ALREADY LOGGED IN
        val currentUser : FirebaseUser? = auth.currentUser
        if (currentUser != null){
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth


        binding.btnlogin.setOnClickListener {
            val loginemail: String = binding.loginemail.text.toString()
            val loginpass: String = binding.loginpass.text.toString()

            if (loginemail.isEmpty() || loginpass.isEmpty()) {
                Toast.makeText(this, "All Failed not enter", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(loginemail, loginpass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "login Successful", Toast.LENGTH_SHORT).show()
                            var intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "faild ", Toast.LENGTH_SHORT).show()

                        }
                    }

            }
        }

        binding.regitorpageText.setOnClickListener {
            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}