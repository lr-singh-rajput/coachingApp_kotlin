package com.shukhibhavtu.coaching_managmant


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.shukhibhavtu.coaching_managmant.databinding.ActivityAddStudentBinding

class add_studentActivity : AppCompatActivity() {

   // lateinit var amAdView : AdView

    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth  // jo user login hai usi ko data dikhe ga (jis user ka data hai usi ko  )

    private val binding: ActivityAddStudentBinding by lazy {
        ActivityAddStudentBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backAdd.setOnClickListener{
            finish()
        }

        /*

        amAdView = findViewById(R.id.ads)
        val adRequest = AdRequest.Builder().build()
        amAdView.loadAd(adRequest)

        amAdView.adListener = object: AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
                super.onAdFailedToLoad(adError)
                amAdView.loadAd(adRequest)

            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded()
                Toast.makeText(this@add_studentActivity, "loaded ads", Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        }

*/
       //ad end

        //Initialaize Firebasse database reference
        databaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()  // jo user login hai usi ko data dikhe ga (jis user ka data hai usi ko  )


        binding.btnSubmit.setOnClickListener{
//            var intent = Intent(this,students_listActivity::class.java)
//            startActivity(intent)

            // text ko server par dalna yaha se le kar
            val name : String = binding.name.text.toString()
            val fname : String = binding.fathername.text.toString()
            val mnumber : String = binding.number.text.toString()
            val group : String = binding.group.text.toString()
            val tfees : String = binding.fees.text.toString()
            val tpaid : String = binding.paid.text.toString()

            if (name.isEmpty() || fname.isEmpty() ||mnumber.isEmpty()||group.isEmpty()||tfees.isEmpty() ){

                Toast.makeText(this, "All Failed not enter",Toast.LENGTH_SHORT).show()
            }else{
                val currencyUsage:FirebaseUser? = auth.currentUser
                currencyUsage?. let { user->
                    // Generate aunique key for the data
                    val noteKey:String? = databaseReference.child("users").child(user.uid).child(group).push().key
                    // data item instance
                   val noteItem = NoteItem(name,fname,mnumber,group,tfees,tpaid,noteKey?:"")
                    if (noteKey!= null)
                        //Add item
                         databaseReference.child("users").child(user.uid).child(group).child(noteKey).setValue(noteItem)
                             .addOnCompleteListener{ task ->
                                 if (task.isSuccessful) {
                                     Toast.makeText(this, "save as", Toast.LENGTH_SHORT).show()
                                     finish()

                                     }else{
                                     Toast.makeText(this, "not save ", Toast.LENGTH_SHORT).show()
                                     }
                                 }
                             }    
                }
            }



        }
    }


