package com.yohanesrizky.yorinotes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yohanesrizky.core.data.Note
import com.yohanesrizky.yorinotes.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class NoteListAdapter(private val noteList:ArrayList<Note>,val action: NoteListAction):RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {


    inner class  NoteViewHolder(val view: ItemNoteBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        with(holder){
            with(noteList[position]){
                view.titleNote.text = title
                view.contentNote.text = content
                view.wordCount.text = "Words $wordCount"

                val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
                val resultDate = Date(updateTime)
                view.datenote.text = "Last update: ${sdf.format(resultDate)}"
                view.noteLayout.setOnClickListener {
                    action.onClick(id)
                }
            }
        }
    }

    override fun getItemCount(): Int {
return noteList.count()
    }

    fun updateNotes(newNotes:List<Note>){
        noteList.clear()
        noteList.addAll(newNotes)
        notifyDataSetChanged()
    }


}