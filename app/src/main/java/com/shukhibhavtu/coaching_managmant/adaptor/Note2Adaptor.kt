package com.shukhibhavtu.coaching_managmant.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shukhibhavtu.coaching_managmant.databinding.RvGroupsBinding


class Note2Adaptor(private var items: MutableList<String>) :
    RecyclerView.Adapter<Note2Adaptor.Note2VievHolder>(){

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(item: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Note2VievHolder {
        val binding = RvGroupsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Note2VievHolder(binding)
    }
    override fun getItemCount(): Int {
        return items.size



    }
    override fun onBindViewHolder(holder: Note2VievHolder, position: Int) {
        val item=items[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            listener?.onItemClick(item)
        }

    }
    class Note2VievHolder(val binding: RvGroupsBinding ):RecyclerView.ViewHolder(binding.root){
        fun bind(item: String) {
            //binding.showgroupname.text=note.group
            binding.showgroupname.text= item

        }

    }
}