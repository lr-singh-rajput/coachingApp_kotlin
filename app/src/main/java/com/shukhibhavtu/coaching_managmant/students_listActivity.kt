package com.shukhibhavtu.coaching_managmant


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.shukhibhavtu.coaching_managmant.databinding.ActivityStudentsListBinding
import com.shukhibhavtu.coaching_managmant.databinding.DialogUpdateNoteBinding



class students_listActivity : AppCompatActivity(),
    NoteAdaptor.OnItemClickListener
    {
    private val binding: ActivityStudentsListBinding by lazy {
        ActivityStudentsListBinding.inflate(layoutInflater)
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

      // BACK BUTTEN
        binding.backRv.setOnClickListener{
            finish()
        }



        recyclerView = binding.rv
        recyclerView.layoutManager = LinearLayoutManager(this)

        //initialize fitebase database refrence
        databaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        val userUid = intent.getStringExtra("userUid")
        val group = intent.getStringExtra("group")

        val currencyUsage = auth.currentUser
        currencyUsage?.let { user ->
            group?.let {
                val childItemsReference =
                    databaseReference.child("users").child(user.uid).child(it)


             //   val childItemsReference = group?.let {
              //  databaseReference.child("users").child(user.uid).child(it)

                childItemsReference.addValueEventListener(object : ValueEventListener {

                   override fun onDataChange(snapshot: DataSnapshot) {
                        val noteList = mutableListOf<NoteItem>()

                        for (childSnapshot in snapshot.children) {
                            val noteItem = childSnapshot.getValue(NoteItem::class.java)
                            noteItem?.let { noteList.add(it) }
                        }

                        val adapter = NoteAdaptor(noteList,this@students_listActivity)
                        recyclerView.adapter = adapter
                    }
                   override fun onCancelled(error: DatabaseError) {
                        // Handle error
                    }

                })
            }





          /*  val noteReference = databaseReference.child("users").child(user.uid).child("notes")

            noteReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val noteList = mutableListOf<NoteItem>()
                    for (noteSnapshot in snapshot.children) {
                        val note = noteSnapshot.getValue(NoteItem::class.java)
                        note?.let {
                            noteList.add(it)
                        }

                    }
                    val adapter = NoteAdaptor(noteList,this@students_listActivity)
                    recyclerView.adapter = adapter
                }

                override fun onCancelled(error: DatabaseError) {

                }*/


            }
        }

        override fun onDeleteClick(noteId: String) {
            val dialog = AlertDialog.Builder(this@students_listActivity)
                dialog.setTitle("Delete")
                dialog.setMessage("Delete this item .. ")
                dialog.setCancelable(false)
                dialog.setIcon(R.drawable.delete_24)

            dialog.setNegativeButton("no") { _, _ ->
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()


            }

            dialog.setPositiveButton("yas") {_,_->

                     val userUid = intent.getStringExtra("userUid")
                   val group = intent.getStringExtra("group")

                   val currencyUsage = auth.currentUser
                   currencyUsage?.let { user ->
                       group?.let {
                           val childItemsReference =
                               databaseReference.child("users").child(user.uid).child(it)
                           childItemsReference.child(noteId).removeValue()
                       }

                   }


                    Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
            }

            val dialogBox =  dialog.create()
                dialogBox.show()


        }

        override fun onUpdateClick(
            noteId: String,
            currentName: String,
            currentFname: String,
            currentNumber: String,
            currentGroup: String,
            currentTfees: String,
            currentTpaid: String
        ) {
            // dialog show karne ke code
            val dialogBinding = DialogUpdateNoteBinding.inflate(LayoutInflater.from(this))
            // update dailog ki binding ki
            val dialog = AlertDialog.Builder(this).setView(dialogBinding.root)
                .setTitle("UPDATE ")
                .setPositiveButton("UPdate"){dialog, _ ->
                    val newName = dialogBinding.edtiName.text.toString()
                    val newFname = dialogBinding.edtiFathername.text.toString()
                    val newNumber = dialogBinding.editNumber.text.toString()
                    val newGroup = dialogBinding.editGroup.text.toString()
                    val newTfees = dialogBinding.editRupi.text.toString()
                    val newTpaid = dialogBinding.editPaid.text.toString()

                    updateNoteDatabase(noteId,newName,newFname,newNumber,newGroup,newTfees,newTpaid)
                    dialog.dismiss()
                }
                .setNegativeButton("Cancell"){dialog, _ ->
                    dialog.dismiss()
                }
                .create()
            dialogBinding.edtiName.setText(currentName)
            dialogBinding.edtiFathername.setText(currentFname)
            dialogBinding.editNumber.setText(currentNumber)
            dialogBinding.editGroup.setText(currentGroup)
            dialogBinding.editRupi.setText(currentTfees)
            dialogBinding.editPaid.setText(currentTpaid)

            dialog.show()
        }

        private fun updateNoteDatabase(
            noteId: String,
            newName: String,
            newFname: String,
            newNumber: String,
            newGroup: String,
            newTfees: String,
            newTpaid: String
        ) {
            // update student code

            val userUid = intent.getStringExtra("userUid")
            val group = intent.getStringExtra("group")


            val currencyUsage = auth.currentUser
            currencyUsage?.let { user ->
                group?.let {
                    val childItemsReference =
                        databaseReference.child("users").child(user.uid).child(it)
                val updateNote = NoteItem(newName,newFname,newNumber,newGroup,newTfees,newTpaid,noteId)
                    childItemsReference.child(noteId).setValue(updateNote)
                    .addOnCompleteListener { task->
                        if(task.isSuccessful){
                            Toast.makeText(this, "update student", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this, "faild update", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            // yha tak hai update student code
        }
        }
    }
/*
        override fun onDeleteClick(noteId: String) {
       val currentUser= auth.currentUser
            currentUser?.let {user->
                val noteRefrence = databaseReference.child("users").child(user.uid).child("notes")
                    noteRefrence.child(noteId).removeValue()
            }
        }

    override fun onUpdateClick(noteId: String, currentName: String, currentFname: String,  currentNumber: String,currentGroup: String, currentTfees: String, currentTpaid: String,) {
       // dialog show karne ke code
       val dialogBinding = DialogUpdateNoteBinding.inflate(LayoutInflater.from(this))
       // update dailog ki binding ki
       val dialog = AlertDialog.Builder(this).setView(dialogBinding.root)
           .setTitle("UPDATE ")
           .setPositiveButton("UPdate"){dialog, _ ->
               val newName = dialogBinding.edtiName.text.toString()
               val newFname = dialogBinding.edtiFathername.text.toString()
               val newNumber = dialogBinding.editNumber.text.toString()
               val newGroup = dialogBinding.editGroup.text.toString()
               val newTfees = dialogBinding.editRupi.text.toString()
               val newTpaid = dialogBinding.editPaid.text.toString()

               updateNoteDatabase(noteId,newName,newFname,newNumber,newGroup,newTfees,newTpaid)
                dialog.dismiss()
           }
           .setNegativeButton("Cancell"){dialog, _ ->
               dialog.dismiss()
           }
            .create()
       dialogBinding.edtiName.setText(currentName)
       dialogBinding.edtiFathername.setText(currentFname)
       dialogBinding.editNumber.setText(currentNumber)
       dialogBinding.editGroup.setText(currentGroup)
       dialogBinding.editRupi.setText(currentTfees)
       dialogBinding.editPaid.setText(currentTpaid)

       dialog.show()
    }

        private fun updateNoteDatabase(
            noteId: String,
            newName: String,
            newFname: String,
            newNumber: String,
            newGroup: String,
            newTfees: String,
            newTpaid: String
        ) {
            // update student code
            val currentUser= auth.currentUser
            currentUser?.let {user->
                val noteReference = databaseReference.child("users").child(user.uid).child("notes")
                val updateNote = NoteItem(newName,newFname,newNumber,newGroup,newTfees,newTpaid,noteId)
                noteReference.child(noteId).setValue(updateNote)
                    .addOnCompleteListener { task->
                        if(task.isSuccessful){
                            Toast.makeText(this, "update student", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this, "faild update", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            // yha tak hai update student code
        }


    }
*/
