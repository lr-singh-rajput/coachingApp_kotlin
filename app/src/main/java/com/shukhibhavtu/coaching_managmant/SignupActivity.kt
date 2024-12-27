package com.shukhibhavtu.coaching_managmant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.shukhibhavtu.coaching_managmant.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private val binding : ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth

    private lateinit var databaseReference: DatabaseReference

// ...


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize Firebase Auth
        //auth = Firebase.auth
        databaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        binding.btnAdduser.setOnClickListener {
            val registarname: String = binding.registarName.text.toString()
            val registaremail: String = binding.registaremail.text.toString()
            val registarpass: String = binding.registarpass.text.toString()
            val registarRepass: String = binding.registarRepass.text.toString()

            if (registarname.isEmpty() || registaremail.isEmpty() || registarpass.isEmpty() || registarRepass.isEmpty()) {
                Toast.makeText(this, "All Failed not enter", Toast.LENGTH_SHORT).show()
            } else if (registarpass != registarRepass) {
                Toast.makeText(this, "password not same", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(registaremail, registarpass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val currencyUsage: FirebaseUser? = auth.currentUser
                            currencyUsage?.let { user ->
                                // Generate aunique key for the data
                                val noteKey: String? =
                                    databaseReference.child("login").child(user.uid).child("detail")
                                        .push().key
                                // data item instance
                                val userData = UserData(registarname, registaremail)
                                if (noteKey != null)
                                //Add item
                                    databaseReference.child("login").child(user.uid)
                                        .child(noteKey).setValue(userData)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                Toast.makeText(this, "save as", Toast.LENGTH_SHORT)
                                                    .show()
                                                finish()
                                            } else {
                                                Toast.makeText(
                                                    this,
                                                    "not save ",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }

                                            /*      val userId = user.uid
                             //  val userData=UserData(
                                  // registarname, registaremail
                              // )
                           userReference.child("userId").setValue(userData)

                          */
                                        }




                                Toast.makeText(this, "Regitration Successful", Toast.LENGTH_SHORT)
                                    .show()
                                var intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }

                        }
                        }

                    }

            }
            binding.btnRefarloginpage.setOnClickListener {
                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
