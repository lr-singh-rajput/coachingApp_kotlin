package com.shukhibhavtu.coaching_managmant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shukhibhavtu.coaching_managmant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   // lateinit var mAdView : AdView
    //lateinit var AdView : AdView
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

  //Ads code
      /*  MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        mAdView.adListener = object: AdListener() {
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
                mAdView.loadAd(adRequest)
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded()
                Toast.makeText(this@MainActivity, "loaded ads", Toast.LENGTH_SHORT).show()

            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        }

        //end Ads code
        //2ad start code


        AdView = findViewById(R.id.secontAd)
       // val adRequest = AdRequest.Builder().build()
     //   AdView.LoadAd(adRequest)
            AdView.loadAd(adRequest)
        AdView.adListener = object: AdListener() {
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
                AdView.loadAd(adRequest)
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded()
                Toast.makeText(this@MainActivity, "loaded ads", Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        }

        //end code 2Ad
        */



        binding.addstudentbtn.setOnClickListener {
            var intent = Intent(this,add_studentActivity::class.java)
            startActivity(intent)
        }
        binding.groupBtn.setOnClickListener {
            var intent = Intent(this,Group_list_Activity::class.java)
            startActivity(intent)
        }
      //  binding.addgroupbtn.setOnClickListener {
       //     var intent = Intent(this,add_groupActivity::class.java)
       //     startActivity(intent)
       // }
        binding.allstudentlist.setOnClickListener {
           var intent = Intent(this,Group_list_Activity::class.java)
          startActivity(intent)
        }

    }
}