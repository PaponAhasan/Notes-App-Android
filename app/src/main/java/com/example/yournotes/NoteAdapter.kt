package com.example.yournotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val context: Context, private val listener:INoteRvAdapter): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(currentNote: Note) {
            val noteDate = itemView.findViewById<TextView>(R.id.noteDate)
            val noteHeader = itemView.findViewById<TextView>(R.id.noteHeader)
            val noteBody = itemView.findViewById<TextView>(R.id.noteBody)
            val deleteImage = itemView.findViewById<ImageView>(R.id.noteDelete)

            noteHeader.text = currentNote.headerText
            noteBody.text = currentNote.bodyText
            deleteImage.setOnClickListener {
                listener.deleteNoteClicked(currentNote)
            }
        }
    }

    fun updateNoteList(newNodeList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newNodeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.bind(currentNote)
    }
}

interface INoteRvAdapter{
    fun deleteNoteClicked(note: Note)
    fun addNoteClicked(note: Note)
}