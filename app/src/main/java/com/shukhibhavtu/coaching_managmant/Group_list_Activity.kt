package com.shukhibhavtu.coaching_managmant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.shukhibhavtu.coaching_managmant.adaptor.Note2Adaptor
import com.shukhibhavtu.coaching_managmant.databinding.ActivityGroupListBinding


class Group_list_Activity : AppCompatActivity()
{
    private val binding: ActivityGroupListBinding by lazy {
        ActivityGroupListBinding.inflate(layoutInflater)
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // back btn
        binding.backgpBtn.setOnClickListener{
            finish()
        }
        recyclerView = binding.rvGroup
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Enable disk persistence for offline data
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true)


        //initialize fitebase database refrence
        databaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        val currencyUsage = auth.currentUser

        currencyUsage?.let { user ->
            val noteReference = databaseReference.child("users").child(user.uid)
            // Enable offline data persistence
         //   noteReference.keepSynced(true)
            noteReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val groupList = mutableListOf<String>()

                    for (groupSnapshot in snapshot.children) {
                        val group = groupSnapshot.key
                        group?.let { groupList.add(it) }
                    }
                    val adapter = Note2Adaptor(groupList)
                    recyclerView.adapter = adapter

                    adapter.setOnItemClickListener(object : Note2Adaptor.OnItemClickListener {
                        override fun onItemClick(group: String) {
                            // Handle item click, open new activity, and display child items
                            val intent = Intent(this@Group_list_Activity, students_listActivity::class.java)
                            intent.putExtra("userUid", user.uid)
                            intent.putExtra("group", group)
                            startActivity(intent)
                        }
                    })
                }

                override fun onCancelled(error: DatabaseError) {

                }


            }


            )
        }


    }
}