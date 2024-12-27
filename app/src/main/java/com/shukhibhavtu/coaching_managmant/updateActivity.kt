// updateActivity.kt

package com.shukhibhavtu.coaching_managmant

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shukhibhavtu.coaching_managmant.databinding.ActivityUpdateBinding


class updateActivity : AppCompatActivity() {
    private val binding: ActivityUpdateBinding by lazy {
        ActivityUpdateBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    binding.cancelBtn.setOnClickListener {
        var intent = Intent(this,students_listActivity::class.java)
        startActivity(intent)
      }
        binding.btnUpdate.setOnClickListener {

        }
    }

}