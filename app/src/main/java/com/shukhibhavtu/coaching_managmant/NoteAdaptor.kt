package com.shukhibhavtu.coaching_managmant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shukhibhavtu.coaching_managmant.databinding.RvStudentBinding



class NoteAdaptor (
    private var notes:List<NoteItem>,
    private val itemClickListener: OnItemClickListener )
    : RecyclerView.Adapter<NoteAdaptor.NoteVievHolder>(){
    interface OnItemClickListener {
       fun onDeleteClick(noteId: String)
        fun onUpdateClick(noteId: String,name: String,fname: String,mnumber: String,group: String,tfees: String,tpaid: String,)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVievHolder {
      val binding =RvStudentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
      return NoteVievHolder(binding)
    }
    override fun getItemCount(): Int {
        return notes.size


    }

    override fun onBindViewHolder(holder: NoteVievHolder, position: Int) {
       val note=notes[position]
        holder.bind(note)
        holder.binding.updateBtn.setOnClickListener {
            itemClickListener.onUpdateClick(note.noteId,note.name,note.fname,note.mnumber,note.group,note.tfees,note.tpaid)
        }
        holder.binding.deleteBtn.setOnClickListener {
            itemClickListener.onDeleteClick(note.noteId)
        }

    }
    class NoteVievHolder(val binding: RvStudentBinding ):RecyclerView.ViewHolder(binding.root){
        fun bind(note: NoteItem) {
            binding.showName.text=note.name
            binding.showFather.text=note.fname
            binding.showGroup.text=note.group
            binding.showNumber.text=note.mnumber
            binding.totalRupi.text=note.tfees
            binding.paidRupi.text=note.tpaid
        }

    }
}